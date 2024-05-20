package net.hibiscus.naturespirit.blocks;


import net.hibiscus.naturespirit.util.HibiscusTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class GrowingBranchingTrunkBlock extends BranchingTrunkBlock implements Fertilizable {

   public static final BooleanProperty SHEARED = BooleanProperty.of("sheared");
   public GrowingBranchingTrunkBlock(Settings settings) {
      super(settings);
      this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(UP, false).with(DOWN, false).with(WATERLOGGED, false).with(SHEARED, false));
   }


   public BlockState getPlacementState(ItemPlacementContext ctx) {
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      return this.withConnectionProperties(ctx.getWorld(), ctx.getBlockPos()).with(WATERLOGGED, fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8);
   }


   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED, SHEARED);
   }

   public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
      return false;
   }

   @Override public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
      return !state.get(UP) && !state.get(EAST) && !state.get(WEST) && !state.get(NORTH) && !state.get(SOUTH) && (world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.east()).isAir() || world.getBlockState(pos.west()).isAir() || world.getBlockState(pos.north()).isAir() || world.getBlockState(pos.south()).isAir());
   }
   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      boolean bl = neighborState.isSideSolid(world, pos, direction.getOpposite(), SideShapeType.CENTER) || neighborState.getBlock() instanceof BranchingTrunkBlock || neighborState.isIn(BlockTags.LEAVES) || (direction == Direction.UP && neighborState.isIn(
              HibiscusTags.Blocks.SUCCULENTS));
      return !state.get(SHEARED) ? state.with(FACING_PROPERTIES.get(direction), bl) : state;
   }

   @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

      Hand hand = player.getActiveHand();
      if(player.getStackInHand(hand).getItem() == Items.SHEARS && !state.get(SHEARED)) {
         ItemStack itemStack = player.getStackInHand(hand);
         if (player instanceof ServerPlayerEntity) {
            Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)player, pos, itemStack);
         }

         world.playSound(player, pos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
         BlockState blockState2 = state.with(SHEARED, true);
         world.setBlockState(pos, blockState2);
         world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState2));
         player.getStackInHand(hand).damage(1, player, LivingEntity.getSlotForHand(player.getActiveHand()));

         return ActionResult.success(world.isClient);
      }
      return super.onUse(state, world, pos, player, hit);
   }

   @Override public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return random.nextFloat() < .35f;
   }

   @Override public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      Direction direction = Direction.random(random);
      if (direction == Direction.DOWN) {direction = Direction.UP;}
      if (world.getBlockState(pos.offset(direction)).isAir() && (world.getBlockState(pos.offset(direction).down()).isAir() || direction == Direction.UP)) {
         world.setBlockState(pos.offset(direction), withConnectionProperties(world, pos.offset(direction)));

         Direction direction2 = Direction.random(random);
         if (world.getBlockState(pos.offset(direction).offset(direction2)).isAir() && direction2 != Direction.DOWN) {

            world.setBlockState(pos.offset(direction).offset(direction2), withConnectionProperties(world, pos.offset(direction).offset(direction2)));

            if (random.nextFloat() < .3) {
               world.setBlockState(pos.offset(direction).offset(direction2).up(), withConnectionProperties(world, pos.offset(direction).offset(direction2).up()));
            }
         }
      }
   }
}