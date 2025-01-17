package net.hibiscus.naturespirit.blocks;

import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.block_entities.PizzaBlockEntity;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusMiscBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PizzaBlock extends Block implements BlockEntityProvider {
   public static final int DEFAULT_COMPARATOR_OUTPUT;
   protected static final VoxelShape[] BITES_TO_SHAPE;
   public static final IntProperty BITES = IntProperty.of("pizza_bites", 0, 3);

   static {
      DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
      BITES_TO_SHAPE = new VoxelShape[]{
              Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D), VoxelShapes.union(
              Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 8.0D),
              Block.createCuboidShape(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D)
      ), Block.createCuboidShape(1.0D, 0.0D, 1.0D, 8.0D, 3.0D, 15.0D), Block.createCuboidShape(1.0D, 0.0D, 8.0D, 8.0D, 3.0D, 15.0D)
      };
   }

   public PizzaBlock(Settings settings) {
      super(settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(BITES, 0));
   }

   protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
      if(player.canConsume(false)) {
         Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE);
         if(optionalPizzaBlockEntity.isPresent()) {
            PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
            player.incrementStat(NatureSpirit.EAT_PIZZA_SLICE);
            int foodAmount = 2;
            float saturationModifier = 0.2F;
            for(Identifier identifier: pizzaBlockEntity.TOPPINGS) {
               foodAmount++;
               saturationModifier = saturationModifier + 0.1F;
            }

            player.getHungerManager().add(foodAmount, saturationModifier);

            int i = state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            if(i < 3) {
               if (!world.isClient()) {
                  world.getServer().getOverworld().updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
               }
               world.setBlockState(pos, state.with(BITES, state.get(BITES) + 1), 2);
               pizzaBlockEntity.markDirty();
            }
            else {
               world.removeBlock(pos, false);
               world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
            return ActionResult.SUCCESS;
         }

      } return ActionResult.PASS;
   }

   public static int getComparatorOutput(int bites) {
      return (7 - bites) * 2;
   }

   @Override public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
      Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE);
      if (optionalPizzaBlockEntity.isPresent()) {
         PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
         int BITE_STATE = state.get(BITES);
         Item item = BITE_STATE == 0 ? HibiscusMiscBlocks.WHOLE_PIZZA : BITE_STATE == 1 ? HibiscusMiscBlocks.THREE_QUARTERS_PIZZA : BITE_STATE == 2 ? HibiscusMiscBlocks.HALF_PIZZA : HibiscusMiscBlocks.QUARTER_PIZZA;
         ItemStack itemStack = new ItemStack(item);
         pizzaBlockEntity.setStackNbt(itemStack, world.getRegistryManager());
         return itemStack;
      }
      return super.getPickStack(world, pos, state);
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
         int BITE_STATE = state.get(BITES);
         return BITES_TO_SHAPE[BITE_STATE];
   }

   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
      Optional<PizzaBlockEntity> optionalPizzaBlockEntity = world.getBlockEntity(pos, HibiscusMiscBlocks.PIZZA_BLOCK_ENTITY_TYPE);
      if (optionalPizzaBlockEntity.isPresent()) {
         PizzaBlockEntity pizzaBlockEntity = optionalPizzaBlockEntity.get();
         ItemStack itemStack = player.getStackInHand(player.getActiveHand());
         Item item = itemStack.getItem();
         if(pizzaBlockEntity.canPlaceTopping(itemStack, pizzaBlockEntity)) {
            if(!player.isCreative()) {
               itemStack.decrement(1);
            }
            if (!world.isClient()) {
               world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            }

            world.playSound(null, pos, SoundEvents.BLOCK_MOSS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            pizzaBlockEntity.TOPPING_NUMBER++;
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            player.incrementStat(Stats.USED.getOrCreateStat(item));

            return ActionResult.SUCCESS;
         }
         if(world.isClient) {
            if(tryEat(world, pos, state, player).isAccepted()) {
               return ActionResult.SUCCESS;
            }

            if(itemStack.isEmpty()) {
               return ActionResult.CONSUME;
            }
         }

         return tryEat(world, pos, state, player);
      }
      return super.onUse(state, world, pos, player, hit);
   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
   }
   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(BITES);
   }
   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      BlockPos pos1 = pos.down();
      return world.getBlockState(pos1).isSolidBlock(world, pos1);
   }

   public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
         int BITE_STATE = state.get(BITES);
         return getComparatorOutput(BITE_STATE);
   }

   public boolean hasComparatorOutput(BlockState state) {
      return true;
   }

   @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
      return new PizzaBlockEntity(pos, state);
   }
}
