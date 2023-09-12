package net.hibiscus.naturespirit.datagen;

import com.google.common.collect.ImmutableList;
import net.hibiscus.naturespirit.NatureSpirit;
import net.hibiscus.naturespirit.blocks.DesertPlantBlock;
import net.hibiscus.naturespirit.blocks.WisteriaVine;
import net.hibiscus.naturespirit.registration.HibiscusBlocksAndItems;
import net.hibiscus.naturespirit.registration.block_registration.HibiscusWoods;
import net.hibiscus.naturespirit.util.HibiscusTags;
import net.hibiscus.naturespirit.world.feature.HibiscusSimpleBlockStateProvider;
import net.hibiscus.naturespirit.world.feature.TurnipRootFeatureConfig;
import net.hibiscus.naturespirit.world.feature.foliage_placer.*;
import net.hibiscus.naturespirit.world.feature.tree_decorator.MapleGroundTreeDecorator;
import net.hibiscus.naturespirit.world.feature.tree_decorator.WisteriaVinesTreeDecorator;
import net.hibiscus.naturespirit.world.feature.trunk.MapleTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.OliveTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.SugiTrunkPlacer;
import net.hibiscus.naturespirit.world.feature.trunk.WisteriaTrunkPlacer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MushroomBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

import static net.hibiscus.naturespirit.util.HibiscusWorldGen.*;

public class HibiscusConfiguredFeatures {

   public static final RegistryKey <ConfiguredFeature <?, ?>> WISTERIA_DELTA = registerKey("water_delta");
   public static final RegistryKey <ConfiguredFeature <?, ?>> SWAMP_DELTA = registerKey("swamp_delta");
   public static final RegistryKey <ConfiguredFeature <?, ?>> MARSH_DELTA = registerKey("marsh_delta");
   public static final RegistryKey <ConfiguredFeature <?, ?>> RIVER_DELTA = registerKey("river_delta");

   public static final RegistryKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE = registerKey("large_redwood_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> REDWOOD_TREE = registerKey("redwood_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> LARGE_REDWOOD_TREE_SPAWN = registerKey("large_redwood_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> REDWOOD_TREE_SPAWN = registerKey("redwood_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> WILLOW_TREE = registerKey("willow_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> WILLOW_TREE_SPAWN = registerKey("willow_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> WHITE_WISTERIA_TREE = registerKey("white_wisteria_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> BLUE_WISTERIA_TREE = registerKey("blue_wisteria_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> PURPLE_WISTERIA_TREE = registerKey("purple_wisteria_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> PINK_WISTERIA_TREE = registerKey("pink_wisteria_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE = registerKey("aspen_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE_BEES = registerKey("aspen_tree_bees");
   public static final RegistryKey <ConfiguredFeature <?, ?>> ASPEN_TREE_SPAWN = registerKey("aspen_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> RED_MAPLE_TREE = registerKey("red_maple_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> ORANGE_MAPLE_TREE = registerKey("orange_maple_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> YELLOW_MAPLE_TREE = registerKey("yellow_maple_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> MAPLE_SPAWN = registerKey("maple_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FIR_TREE = registerKey("fir_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FIR_TREE_SPAWN = registerKey("fir_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> WISTERIA_SPAWN = registerKey("wisteria_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> SUGI_TREE = registerKey("sugi_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> SUGI_SPAWN = registerKey("sugi_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> CYPRESS_TREE = registerKey("cypress_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> CYPRESS_TREE_SPAWN = registerKey("cypress_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> OLIVE_TREE = registerKey("olive_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> OLIVE_TREE_SPAWN = registerKey("olive_tree_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> JOSHUA_TREE = registerKey("joshua_tree");
   public static final RegistryKey <ConfiguredFeature <?, ?>> JOSHUA_TREE_SPAWN = registerKey("joshua_tree_spawn");

   public static final RegistryKey <ConfiguredFeature <?, ?>> OAK_BUSH = registerKey("oak_bush");
   public static final RegistryKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH = registerKey("spruce_bush");
   public static final RegistryKey <ConfiguredFeature <?, ?>> OAK_BUSH_SPAWN = registerKey("oak_bush_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> SPRUCE_BUSH_SPAWN = registerKey("spruce_bush_spawn");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FANCY_OAK_TREE_SPAWN = registerKey("custom_fancy_oak_tree_spawn");


   public static final RegistryKey <ConfiguredFeature <?, ?>> PUMPKIN_PATCH_FEATURE = registerKey("pumpkin_patch_feature");
   public static final RegistryKey <ConfiguredFeature <?, ?>> PUMPKIN_FEATURE = registerKey("pumpkin_feature");

   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_WISTERIA_FOREST = registerKey("flower_wisteria_forest");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_SUGI_FOREST = registerKey("flower_sugi_forest");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_REDWOOD_FOREST = registerKey("flower_redwood_forest");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_LAVENDER_FIELD = registerKey("flower_lavender_field");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_FOXGLOVE_FIELD = registerKey("flower_foxglove_field");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_ERODED_RIVER = registerKey("flower_eroded_river");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_GOLDEN_WILDS = registerKey("flower_golden_wilds");
   public static final RegistryKey <ConfiguredFeature <?, ?>> HUGE_SHIITAKE_MUSHROOM = registerKey("huge_shiitake_mushroom");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_FIR_FOREST = registerKey("flower_fir_forest");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_CYPRESS_FIELDS = registerKey("flower_cypress_fields");
   public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_SCORCHED_GRASS = registerKey("patch_scorched_grass");
   public static final RegistryKey <ConfiguredFeature <?, ?>> PATCH_TALL_SCORCHED_GRASS = registerKey("patch_tall_scorched_grass");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_BLOOMING_DUNES = registerKey("flower_blooming_dunes");
   public static final RegistryKey <ConfiguredFeature <?, ?>> FLOWER_STRATIFIED_DESERT = registerKey("flower_stratified_desert");
   public static final RegistryKey <ConfiguredFeature <?, ?>> CATTAILS = registerKey("cattails");
   public static final RegistryKey <ConfiguredFeature <?, ?>> LOTUS_PLANT = registerKey("lotus_plant");
   public static final RegistryKey <ConfiguredFeature <?, ?>> ROOTED_DESERT_TURNIP = registerKey("rooted_desert_turnip");


   public static void bootstrap(Registerable <ConfiguredFeature <?, ?>> context) {
      var placedFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
      RegistryEntryLookup <Block> holderGetter = context.getRegistryLookup(RegistryKeys.BLOCK);

      register(context, REDWOOD_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.REDWOOD.getLog()),
              new StraightTrunkPlacer(12, 1, 4),
              BlockStateProvider.of(HibiscusWoods.REDWOOD.getLeaves()),
              new SpruceFoliagePlacer(UniformIntProvider.create(1, 3), UniformIntProvider.create(1, 2), UniformIntProvider.create(14, 15)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).ignoreVines().build());

      register(
              context,
              REDWOOD_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.REDWOOD_CHECKED)
              )
      );

      register(context, ASPEN_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.ASPEN.getLog()),
              new StraightTrunkPlacer(14, 2, 5),
              BlockStateProvider.of(HibiscusWoods.ASPEN.getLeaves()),
              new AspenFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(4, 18)),
              new TwoLayersFeatureSize(1, 0, 1)
      ).ignoreVines().build());
      register(context, ASPEN_TREE_BEES, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.ASPEN.getLog()),
              new StraightTrunkPlacer(14, 2, 5),
              BlockStateProvider.of(HibiscusWoods.ASPEN.getLeaves()),
              new AspenFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(4, 18)),
              new TwoLayersFeatureSize(1, 0, 1)
      ).ignoreVines().decorators(List.of(new BeehiveTreeDecorator(1.0F))).build());

      register(
              context,
              ASPEN_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_BEES_CHECKED), 0.01f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ASPEN_CHECKED)
              )
      );

      register(context, RED_MAPLE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.MAPLE.getLog()),
              new MapleTrunkPlacer(9, 2, 0, new WeightedListIntProvider(DataPool
                      . <IntProvider>builder()
                      .add(ConstantIntProvider.create(5), 1)
                      .add(ConstantIntProvider.create(2), 1)
                      .add(ConstantIntProvider.create(3), 1)
                      .add(ConstantIntProvider.create(4), 1)
                      .build()), UniformIntProvider.create(1, 3), UniformIntProvider.create(-5, -4), UniformIntProvider.create(-4, -1)),
              BlockStateProvider.of(HibiscusWoods.MAPLE.getRedMapleLeaves()),
              new MapleFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.26666667F, 0.53333334F),
              new TwoLayersFeatureSize(1, 0, 2)
      ).ignoreVines().decorators(ImmutableList.of(new MapleGroundTreeDecorator(SimpleBlockStateProvider.of(Blocks.PODZOL), SimpleBlockStateProvider.of(Blocks.COARSE_DIRT)))).build());
      register(context, ORANGE_MAPLE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.MAPLE.getLog()),
              new MapleTrunkPlacer(
                      9,
                      2,
                      0,
                      new WeightedListIntProvider(DataPool. <IntProvider>builder().add(ConstantIntProvider.create(5), 1).add(
                              ConstantIntProvider.create(2),
                              1
                      ).add(ConstantIntProvider.create(3), 1).add(ConstantIntProvider.create(4), 1).build()),
                      UniformIntProvider.create(1, 3),
                      UniformIntProvider.create(-5, -4),
                      UniformIntProvider.create(-4, -1)
              ),
              BlockStateProvider.of(HibiscusWoods.MAPLE.getOrangeMapleLeaves()),
              new MapleFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.26666667F, 0.53333334F),
              new TwoLayersFeatureSize(1, 0, 2)
      ).ignoreVines().decorators(ImmutableList.of(new MapleGroundTreeDecorator(SimpleBlockStateProvider.of(Blocks.PODZOL), SimpleBlockStateProvider.of(Blocks.COARSE_DIRT)))).build());
      register(context, YELLOW_MAPLE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.MAPLE.getLog()),
              new MapleTrunkPlacer(
                      9,
                      2,
                      0,
                      new WeightedListIntProvider(DataPool. <IntProvider>builder().add(ConstantIntProvider.create(5), 1).add(
                              ConstantIntProvider.create(2),
                              1
                      ).add(ConstantIntProvider.create(3), 1).add(ConstantIntProvider.create(4), 1).build()),
                      UniformIntProvider.create(1, 3),
                      UniformIntProvider.create(-5, -4),
                      UniformIntProvider.create(-4, -1)
              ),
              BlockStateProvider.of(HibiscusWoods.MAPLE.getYellowMapleLeaves()),
              new MapleFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.26666667F, 0.53333334F),
              new TwoLayersFeatureSize(1, 0, 2)
      ).ignoreVines().decorators(ImmutableList.of(new MapleGroundTreeDecorator(SimpleBlockStateProvider.of(Blocks.PODZOL), SimpleBlockStateProvider.of(Blocks.COARSE_DIRT)))).build());

      register(context,
              MAPLE_SPAWN,
              Feature.SIMPLE_RANDOM_SELECTOR,
              new SimpleRandomFeatureConfig(RegistryEntryList.of(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.RED_MAPLE_CHECKED),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.ORANGE_MAPLE_CHECKED),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.YELLOW_MAPLE_CHECKED),
                      placedFeatureRegistryEntryLookup.getOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED)
              ))
      );

      register(context, CYPRESS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.CYPRESS.getLog()),
              new StraightTrunkPlacer(11, 1, 2),
              BlockStateProvider.of(HibiscusWoods.CYPRESS.getLeaves()),
              new CypressFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(3, 3), UniformIntProvider.create(11, 13)),
              new TwoLayersFeatureSize(1, 0, 1)
      ).ignoreVines().build());


      register(context, FIR_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.FIR.getLog()),
              new StraightTrunkPlacer(10, 1, 2),
              BlockStateProvider.of(HibiscusWoods.FIR.getLeaves()),
              new FirFoliagePlacer(UniformIntProvider.create(2, 2), UniformIntProvider.create(2, 3), UniformIntProvider.create(3, 12)),
              new TwoLayersFeatureSize(1, 0, 1)
      ).ignoreVines().build());

      register(context,
              FIR_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.FIR_CHECKED)
              )
      );


      register(context,
              WISTERIA_DELTA,
              HIBISCUS_DELTA_FEATURE,
              new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.COARSE_DIRT.getDefaultState(), UniformIntProvider.create(5, 8), UniformIntProvider.create(0, 4))
      );
      register(context,
              SWAMP_DELTA,
              HIBISCUS_DELTA_FEATURE,
              new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.MUD.getDefaultState(), UniformIntProvider.create(2, 12), UniformIntProvider.create(1, 3))
      );
      register(context,
              MARSH_DELTA,
              HIBISCUS_DELTA_FEATURE,
              new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.MUD.getDefaultState(), UniformIntProvider.create(3, 14), UniformIntProvider.create(1, 3))
      );
      register(context,
              RIVER_DELTA,
              HIBISCUS_DELTA_FEATURE,
              new DeltaFeatureConfig(Blocks.WATER.getDefaultState(), Blocks.COARSE_DIRT.getDefaultState(), UniformIntProvider.create(2, 6), UniformIntProvider.create(1, 3))
      );

      register(context, LARGE_REDWOOD_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.REDWOOD.getLog()),
              new GiantTrunkPlacer(18, 2, 17),
              BlockStateProvider.of(HibiscusWoods.REDWOOD.getLeaves()),
              new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(6, 12)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL)))).build());

      register(
              context,
              LARGE_REDWOOD_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.LARGE_REDWOOD_CHECKED)
              )
      );


      register(context, WILLOW_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.WILLOW.getLog()),
              new LargeOakTrunkPlacer(10, 3, 5),
              BlockStateProvider.of(HibiscusWoods.WILLOW.getLeaves()),
              new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(3, 0, 2, OptionalInt.of(5))
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.65F,
              new HibiscusSimpleBlockStateProvider(HibiscusWoods.WILLOW.getWillowVinesPlant().getDefaultState()),
              new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusWoods.WILLOW.getWillowVines().getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(23, 25)),
              5
      ))).ignoreVines().build());
      register(context,
              WILLOW_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WILLOW_CHECKED)
              )
      );


      register(context, WHITE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.WISTERIA.getLog()),
              new WisteriaTrunkPlacer(7, 3, 4, UniformIntProvider.create(1, 6), 0.80F, UniformIntProvider.create(7, 10), holderGetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
              BlockStateProvider.of(HibiscusWoods.WISTERIA.getWhiteWisteriaLeaves()),
              new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusWoods.WISTERIA.getWhiteWisteriaVinesPlant().getDefaultState()),
              new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusWoods.WISTERIA.getWhiteWisteriaVines().getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
              2
      ))).ignoreVines().build());

      register(context, PINK_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.WISTERIA.getLog()),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformIntProvider.create(1, 6),
                      0.80F,
                      UniformIntProvider.create(7, 10),
                      Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.of(HibiscusWoods.WISTERIA.getPinkWisteriaLeaves()),
              new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusWoods.WISTERIA.getPinkWisteriaVinesPlant().getDefaultState()),
              new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusWoods.WISTERIA.getPinkWisteriaVines().getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
              2
      ))).ignoreVines().build());

      register(context, BLUE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.WISTERIA.getLog()),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformIntProvider.create(1, 6),
                      0.80F,
                      UniformIntProvider.create(7, 10),
                      Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.of(HibiscusWoods.WISTERIA.getBlueWisteriaLeaves()),
              new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusWoods.WISTERIA.getBlueWisteriaVinesPlant().getDefaultState()),
              new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusWoods.WISTERIA.getBlueWisteriaVines().getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
              2
      ))).ignoreVines().build());

      register(context, PURPLE_WISTERIA_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.WISTERIA.getLog()),
              new WisteriaTrunkPlacer(7,
                      3,
                      4,
                      UniformIntProvider.create(1, 6),
                      0.80F,
                      UniformIntProvider.create(7, 10),
                      Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
              ),
              BlockStateProvider.of(HibiscusWoods.WISTERIA.getPurpleWisteriaLeaves()),
              new WisteriaFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(2, 0, 2)
      ).decorators(List.of(new WisteriaVinesTreeDecorator(0.45F,
              new HibiscusSimpleBlockStateProvider(HibiscusWoods.WISTERIA.getPurpleWisteriaVinesPlant().getDefaultState()),
              new RandomizedIntBlockStateProvider(BlockStateProvider.of(HibiscusWoods.WISTERIA.getPurpleWisteriaVines().getDefaultState()), WisteriaVine.AGE, UniformIntProvider.create(22, 25)),
              2
      ))).ignoreVines().build());

      register(
              context,
              WISTERIA_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED), 0.10f),
                      new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.BLUE_WISTERIA_CHECKED), 0.325f),
                      new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PURPLE_WISTERIA_CHECKED), 0.325f),
                      new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.PINK_WISTERIA_CHECKED), 0.25f)
              ), placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.WHITE_WISTERIA_CHECKED))
      );

      register(context, SUGI_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.SUGI.getLog()),
              new SugiTrunkPlacer(10, 1, 1, UniformIntProvider.create(4, 6), .85F, UniformIntProvider.create(4, 5), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
              BlockStateProvider.of(HibiscusWoods.SUGI.getLeaves()),
              new SugiFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0)),
              new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
      ).ignoreVines().build());

      register(context,
              SUGI_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED), 0.325f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SUGI_CHECKED)
              )
      );

      register(context, OLIVE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(HibiscusWoods.OLIVE.getLog()),
              new OliveTrunkPlacer(4, 1, 2, UniformIntProvider.create(4, 6), .85F, UniformIntProvider.create(4, 5), Registries.BLOCK.getOrCreateEntryList(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
              BlockStateProvider.of(HibiscusWoods.OLIVE.getLeaves()),
              new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 60),
              new TwoLayersFeatureSize(1, 0, 1, OptionalInt.of(5))
      ).ignoreVines().build());

      register(context,
              OLIVE_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OLIVE_CHECKED), 0.325f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OLIVE_CHECKED)
              )
      );

      register(context, JOSHUA_TREE, JOSHUA_TREE_FEATURE, FeatureConfig.DEFAULT);

      register(
              context,
              JOSHUA_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.JOSHUA_CHECKED), 0.325f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.JOSHUA_CHECKED)
              )
      );

      register(
              context,
              CYPRESS_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.CYPRESS_CHECKED), 0.325f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.CYPRESS_CHECKED)
              )
      );

      register(context,
              FANCY_OAK_TREE_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(TreePlacedFeatures.FANCY_OAK_BEES), 0.03F)),
                      placedFeatureRegistryEntryLookup.getOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED)
              )
      );

      register(context, OAK_BUSH, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.OAK_LOG),
              new StraightTrunkPlacer(1, 0, 0),
              BlockStateProvider.of(Blocks.OAK_LEAVES),
              new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
              new TwoLayersFeatureSize(0, 0, 0)
      ).build());
      register(context,
              OAK_BUSH_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED)
              )
      );

      register(context, SPRUCE_BUSH, Feature.TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.SPRUCE_LOG),
              new StraightTrunkPlacer(1, 0, 0),
              BlockStateProvider.of(Blocks.SPRUCE_LEAVES),
              new RandomSpreadFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(2), 75),
              new TwoLayersFeatureSize(1, 0, 1)
      ).build());
      register(
              context,
              SPRUCE_BUSH_SPAWN,
              Feature.RANDOM_SELECTOR,
              new RandomFeatureConfig(List.of(new RandomFeatureEntry(placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.OAK_BUSH_CHECKED), 0.5f)),
                      placedFeatureRegistryEntryLookup.getOrThrow(HibiscusPlacedFeatures.SPRUCE_BUSH_CHECKED)
              )
      );

      register(context,
              FLOWER_WISTERIA_FOREST,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(96,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.ALLIUM.getDefaultState(),
                                              HibiscusBlocksAndItems.BLUEBELL.getDefaultState(),
                                              HibiscusBlocksAndItems.ANEMONE.getDefaultState(),
                                              Blocks.OXEYE_DAISY.getDefaultState(),
                                              Blocks.PINK_TULIP.getDefaultState(),
                                              HibiscusBlocksAndItems.SNAPDRAGON.getDefaultState(),
                                              HibiscusBlocksAndItems.GARDENIA.getDefaultState(),
                                              HibiscusBlocksAndItems.LAVENDER.getDefaultState(),
                                              HibiscusBlocksAndItems.HIBISCUS.getDefaultState(),
                                              Blocks.CORNFLOWER.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_SUGI_FOREST,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(45,
                      8,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                                              Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                                              HibiscusBlocksAndItems.GARDENIA.getDefaultState(),
                                              Blocks.AZURE_BLUET.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_LAVENDER_FIELD,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(120,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILAC.getDefaultState(),
                                              HibiscusBlocksAndItems.ANEMONE.getDefaultState(),
                                              HibiscusBlocksAndItems.LAVENDER.getDefaultState(),
                                              Blocks.GRASS.getDefaultState(),
                                              Blocks.PEONY.getDefaultState()
                                      )
                              ))
                      )
              )
      );
      register(context,
              FLOWER_FOXGLOVE_FIELD,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(120,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILAC.getDefaultState(),
                                              Blocks.GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.FOXGLOVE.getDefaultState(),
                                              Blocks.TALL_GRASS.getDefaultState(),
                                              Blocks.PEONY.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_ERODED_RIVER,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(60,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                                              HibiscusBlocksAndItems.BLEEDING_HEART.getDefaultState(),
                                              HibiscusBlocksAndItems.ANEMONE.getDefaultState(),
                                              HibiscusBlocksAndItems.HIBISCUS.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_REDWOOD_FOREST,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(36,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocksAndItems.BLUEBELL.getDefaultState(),
                                              Blocks.ORANGE_TULIP.getDefaultState(),
                                              Blocks.GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.CARNATION.getDefaultState(),
                                              HibiscusBlocksAndItems.GARDENIA.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_GOLDEN_WILDS,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(36,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocksAndItems.TIGER_LILY.getDefaultState(),
                                              Blocks.ORANGE_TULIP.getDefaultState(),
                                              HibiscusBlocksAndItems.FLAXEN_FERN.getDefaultState(),
                                              Blocks.DANDELION.getDefaultState(),
                                              HibiscusBlocksAndItems.MARIGOLD.getDefaultState(),
                                              HibiscusBlocksAndItems.LARGE_FLAXEN_FERN.getDefaultState(),
                                              HibiscusBlocksAndItems.CARNATION.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              HUGE_SHIITAKE_MUSHROOM,
              HUGE_SHIITAKE_MUSHROOM_FEATURE,
              new HugeMushroomFeatureConfig(BlockStateProvider.of(HibiscusBlocksAndItems.SHIITAKE_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false)),
                      BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)),
                      2
              )
      );
      register(context,
              FLOWER_FIR_FOREST,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(36,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.LILY_OF_THE_VALLEY.getDefaultState(),
                                              Blocks.OXEYE_DAISY.getDefaultState(),
                                              Blocks.OXEYE_DAISY.getDefaultState(),
                                              HibiscusBlocksAndItems.GARDENIA.getDefaultState(),
                                              Blocks.ALLIUM.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_CYPRESS_FIELDS,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(120,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(Blocks.GRASS.getDefaultState(),
                                              Blocks.TALL_GRASS.getDefaultState(),
                                              Blocks.POPPY.getDefaultState(),
                                              HibiscusBlocksAndItems.CARNATION.getDefaultState(),
                                              Blocks.POPPY.getDefaultState(),
                                              Blocks.GRASS.getDefaultState(),
                                              Blocks.POPPY.getDefaultState(),
                                              HibiscusBlocksAndItems.CARNATION.getDefaultState(),
                                              Blocks.POPPY.getDefaultState(),
                                              Blocks.TALL_GRASS.getDefaultState(),
                                              Blocks.GRASS.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              PATCH_SCORCHED_GRASS,
              Feature.RANDOM_PATCH,
              new RandomPatchFeatureConfig(32, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(HibiscusBlocksAndItems.SCORCHED_GRASS))))
      );
      register(context,
              PATCH_TALL_SCORCHED_GRASS,
              Feature.RANDOM_PATCH,
              new RandomPatchFeatureConfig(32, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(HibiscusBlocksAndItems.SCORCHED_GRASS))))
      );


      register(context,
              FLOWER_BLOOMING_DUNES,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(120,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocksAndItems.SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.TALL_SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.YELLOW_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.PURPLE_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.YELLOW_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.YELLOW_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.PURPLE_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.YELLOW_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.TALL_SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.SCORCHED_GRASS.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              FLOWER_STRATIFIED_DESERT,
              Feature.FLOWER,
              new RandomPatchFeatureConfig(10,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(new NoiseBlockStateProvider(2445L,
                                      new DoublePerlinNoiseSampler.NoiseParameters(0, 1.0D),
                                      0.030833334F,
                                      List.of(HibiscusBlocksAndItems.SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.YELLOW_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.TALL_SCORCHED_GRASS.getDefaultState(),
                                              HibiscusBlocksAndItems.PURPLE_WILDFLOWER.getDefaultState(),
                                              HibiscusBlocksAndItems.SCORCHED_GRASS.getDefaultState()
                                      )
                              ))
                      )
              )
      );

      register(context,
              CATTAILS,
              Feature.RANDOM_PATCH,
              new RandomPatchFeatureConfig(90,
                      6,
                      2,
                      PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                              new SimpleBlockFeatureConfig(BlockStateProvider.of(HibiscusBlocksAndItems.CATTAIL)),
                              BlockPredicate.allOf(BlockPredicate.wouldSurvive(HibiscusBlocksAndItems.CATTAIL.getDefaultState(), BlockPos.ORIGIN), BlockPredicate.IS_AIR_OR_WATER, BlockPredicate.anyOf(
                                      BlockPredicate.matchingFluids(new BlockPos(1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                                      BlockPredicate.matchingFluids(new BlockPos(-1, -1, 0), Fluids.WATER, Fluids.FLOWING_WATER),
                                      BlockPredicate.matchingFluids(new BlockPos(0, -1, 1), Fluids.WATER, Fluids.FLOWING_WATER),
                                      BlockPredicate.matchingFluids(new BlockPos(0, -1, -1), Fluids.WATER, Fluids.FLOWING_WATER),
                                      BlockPredicate.matchingFluids(new BlockPos(0, 0, 0), Fluids.WATER, Fluids.FLOWING_WATER)
                              ))
                      )
              )
      );


      register(context, LOTUS_PLANT, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(20, 6, 2, PlacedFeatures.createEntry(LOTUS_PLANT_FEATURE, FeatureConfig.DEFAULT)));

      register(context,
              ROOTED_DESERT_TURNIP,
              HIBISCUS_TURNIP_ROOT_FEATURE,
              new TurnipRootFeatureConfig(PlacedFeatures.createEntry(Feature.NO_BONEMEAL_FLOWER, new RandomPatchFeatureConfig(30, 3, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                      new SimpleBlockFeatureConfig(BlockStateProvider.of(HibiscusBlocksAndItems.DESERT_TURNIP_STEM.getDefaultState().with(DesertPlantBlock.AGE, 7))),
                      PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP
              )), new PlacementModifier[0]),
                      3,
                      3,
                      HibiscusTags.Blocks.TURNIP_ROOT_REPLACEABLE,
                      BlockStateProvider.of(HibiscusBlocksAndItems.DESERT_TURNIP_ROOT_BLOCK),
                      BlockStateProvider.of(HibiscusBlocksAndItems.DESERT_TURNIP_BLOCK),
                      20,
                      20,
                      3,
                      2,
                      BlockStateProvider.of(Blocks.HANGING_ROOTS),
                      20,
                      35,
                      2,
                      BlockPredicate.bothOf(
                              BlockPredicate.eitherOf(BlockPredicate.matchingBlocks(List.of(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR)), BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE)),
                              BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), HibiscusTags.Blocks.TURNIP_STEM_GROWS_ON)
                      )
              )
      );

      register(context,
              PUMPKIN_PATCH_FEATURE,
              HIBISCUS_PUMPKIN_PATCH_FEATURE,
              new OreFeatureConfig(new BlockMatchRuleTest(Blocks.AIR), Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 43, 0)
      );
      register(context, PUMPKIN_FEATURE, HIBISCUS_LARGE_PUMPKIN_FEATURE, new DefaultFeatureConfig());

   }

   private static <FC extends FeatureConfig, F extends Feature <FC>> void register(Registerable <ConfiguredFeature <?, ?>> context, RegistryKey <ConfiguredFeature <?, ?>> key, F feature, FC configuration) {
      context.register(key, new ConfiguredFeature <>(feature, configuration));
   }

   public static RegistryKey <ConfiguredFeature <?, ?>> registerKey(String name) {
      return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(NatureSpirit.MOD_ID, name));
   }

   public static void registerConfiguredFeatures() {
      System.out.println("Registering Configured Features For:" + NatureSpirit.MOD_ID);
   }
}

