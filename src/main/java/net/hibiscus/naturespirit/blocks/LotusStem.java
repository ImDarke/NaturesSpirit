package net.hibiscus.naturespirit.blocks;

import com.mojang.serialization.MapCodec;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.minecraft.block.*;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LotusStem extends AbstractPlantBlock implements Waterloggable {
   public static final IntProperty AGE;
   public static final BooleanProperty WATERLOGGED;
   public static final VoxelShape SHAPE = Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

   static {
      AGE = Properties.AGE_3;
      WATERLOGGED = Properties.WATERLOGGED;
   }

   private final double growthChance;
   public Block headBlock;
   public Direction growthDirection = Direction.UP;

   public LotusStem(Settings properties, Block headBlock) {
      super(properties, Direction.UP, SHAPE, false);
      this.headBlock = headBlock;
      this.growthChance = 0.1D;
      this.setDefaultState(this.stateManager.getDefaultState());
   }

   protected AbstractPlantStemBlock getStem() {
      return (AbstractPlantStemBlock) HibiscusWoods.WISTERIA.getBlueWisteriaVines();
   }

   @Nullable public BlockState getPlacementState(ItemPlacementContext ctx) {
      BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(this.growthDirection));
      FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
      boolean waterlogged = fluidState.isIn(FluidTags.WATER) && fluidState.getLevel() == 8;
      return !blockState.isOf(this.asBlock()) && !blockState.isOf(this.getPlant()) ? this.getRandomGrowthState(ctx.getWorld()).with(WATERLOGGED, waterlogged) : this.asBlock().getDefaultState().with(WATERLOGGED,
              waterlogged
      );
   }

   @Override public boolean canPlaceAt(BlockState state, WorldView levelReader, BlockPos pos) {
      BlockPos blockPos = pos.offset(this.growthDirection.getOpposite());
      BlockState blockState = levelReader.getBlockState(blockPos);
      if(levelReader.getFluidState(pos).isIn(FluidTags.WATER)) {
         return (blockState.isSideSolidFullSquare(levelReader, pos, Direction.UP) || blockState.isOf(this.asBlock())) && !blockState.isOf(Blocks.MAGMA_BLOCK);
      }
      return blockState.isOf(this.asBlock()) || blockState.isIn(BlockTags.DIRT) || blockState.isOf(Blocks.CLAY) || blockState.isOf(Blocks.FARMLAND);
   }

   public BlockState getRandomGrowthState(WorldAccess world) {
      return this.getDefaultState().with(AGE, world.getRandom().nextInt(3));
   }

   public boolean hasRandomTicks(BlockState state) {
      return state.get(AGE) < 3;
   }

   public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if(state.get(AGE) < 3 && random.nextDouble() < this.growthChance) {
         BlockPos blockPos = pos.offset(this.growthDirection);
         if(this.chooseStemState(world.getBlockState(blockPos))) {
            world.setBlockState(blockPos, this.getPlant().getDefaultState());
         }
         else if(world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
            world.setBlockState(blockPos, this.getDefaultState().with(WATERLOGGED, world.getFluidState(blockPos).isIn(FluidTags.WATER)).with(AGE, 2));
            if(this.chooseStemState(world.getBlockState(blockPos.up()))) {
               world.setBlockState(blockPos.up(), this.getPlant().getDefaultState());
            }
         }
         else if(world.getBlockState(blockPos).isOf(this.getPlant())) {
            if(!world.getFluidState(pos).isIn(FluidTags.WATER)) {
               world.setBlockState(blockPos, this.getDefaultState().with(WATERLOGGED, world.getFluidState(blockPos).isIn(FluidTags.WATER)).with(AGE, Math.min(state.get(AGE) + 1, 3)));
               world.setBlockState(blockPos.up(), this.getPlant().getDefaultState());
            }
            else {
               world.setBlockState(pos, this.getDefaultState().with(WATERLOGGED, world.getFluidState(pos).isIn(FluidTags.WATER)).with(AGE, 3));
            }
         }
      }

   }

   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
      if(direction == this.growthDirection.getOpposite() && !state.canPlaceAt(world, pos)) {
         world.scheduleBlockTick(pos, this.asBlock(), 1);
      }

      if(direction != this.growthDirection || !neighborState.isOf(this.asBlock()) && !neighborState.isOf(this.getPlant())) {
         if(this.tickWater) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
         }
      }
      if(direction == this.growthDirection && neighborState.isAir()) {
         world.setBlockState(pos, state.with(LotusStem.AGE, 2), 2);
      }
      return state;
   }

   protected void appendProperties(StateManager.Builder <Block, BlockState> builder) {
      builder.add(AGE).add(WATERLOGGED);
   }

   public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
      return new ItemStack(this.asItem());
   }

   protected BlockState age(BlockState state, Random random) {
      return state.cycle(AGE);
   }

   @Override protected MapCodec <? extends AbstractPlantBlock> getCodec() {
      return null;
   }

   protected BlockState copyState(BlockState from, BlockState to) {
      return to;
   }

   public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
      Optional <BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional2 = this.getStemHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional3 = this.getStemHeadWaterPos2(world, pos, this.asBlock());
      BlockPos blockPos = optional2.isPresent() ? optional2.get().down() : optional3.isPresent() ? optional3.get().down() : optional.orElse(pos);
      boolean lotusPowered = false;
      if (optional.isPresent()) {
         BlockPos blockPos2 = optional.get();
         LotusFlowerBlock lotusFlowerBlock = (LotusFlowerBlock) world.getBlockState(blockPos2).getBlock();
         lotusPowered = lotusFlowerBlock.isPowered(world, blockPos2);
      }
      return (this.chooseStemState(world.getBlockState(blockPos.offset(this.growthDirection))) || world.getFluidState(blockPos.offset(this.growthDirection)).isIn(FluidTags.WATER)) && !lotusPowered;
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      Optional <BlockPos> optional = this.getFlowerHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional2 = this.getStemHeadPos(world, pos, this.asBlock());
      Optional <BlockPos> optional3 = this.getStemHeadWaterPos2(world, pos, this.asBlock());
      BlockPos blockPos = optional2.isPresent() ? optional2.get().down() : optional3.isPresent() ? optional3.get().down() : optional.orElse(pos);
      int i = Math.min(state.get(AGE) + 1, 3);

      if (this.chooseStemState(world.getBlockState(blockPos)) || world.getBlockState(blockPos).isOf(this.getPlant()) || world.getBlockState(blockPos).isOf(this.asBlock()) || world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
         world.setBlockState(blockPos, state.with(AGE, i).with(WATERLOGGED, world.getFluidState(blockPos).isIn(FluidTags.WATER)));
         if(world.getBlockState(blockPos.up()).isOf(Blocks.AIR)) {
            world.setBlockState(blockPos.offset(this.growthDirection, 1), this.getPlant().getDefaultState());
         }
         if(world.getBlockState(blockPos.up()).isOf(Blocks.WATER)) {
            world.setBlockState(blockPos.offset(this.growthDirection, 1), this.asBlock().getDefaultState().with(AGE, i).with(WATERLOGGED, world.getFluidState(blockPos.offset(this.growthDirection, 1)).isIn(FluidTags.WATER)));
         }
         if (optional.isPresent()) {
            BlockPos blockPos2 = optional.get();
            PlayerEntity player = world.getClosestPlayer(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), 1.25D, false);
            if (player != null) {
               player.move(MovementType.SHULKER_BOX, new Vec3d(0, 1.01, 0));
            }
         }
      }

   }

   private Optional <BlockPos> getFlowerHeadPos(BlockView world, BlockPos pos, Block block) {
      return BlockLocating.findColumnEnd(world, pos, block, this.growthDirection, this.getPlant());
   }

   private Optional <BlockPos> getStemHeadPos(BlockView world, BlockPos pos, Block block) {
      return BlockLocating.findColumnEnd(world, pos, block, this.growthDirection, Blocks.AIR);
   }

   public static Optional <BlockPos> getStemHeadWaterPos(BlockView world, BlockPos pos, Block block) {
      return BlockLocating.findColumnEnd(world, pos, Blocks.WATER, Direction.UP, block);
   }

   private Optional <BlockPos> getStemHeadWaterPos2(BlockView world, BlockPos pos, Block block) {
      return BlockLocating.findColumnEnd(world, pos, block, this.growthDirection, Blocks.WATER);
   }
   public Block getPlant() {
      return headBlock;
   }

   public boolean chooseStemState(BlockState state) {
      return state.isAir();
   }

   public FluidState getFluidState(BlockState state) {
      return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
   }
}
