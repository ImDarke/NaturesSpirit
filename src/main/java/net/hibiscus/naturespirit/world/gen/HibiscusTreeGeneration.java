package net.hibiscus.naturespirit.world.gen;

import net.hibiscus.naturespirit.world.feature.HibiscusPlacedFeatures;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class HibiscusTreeGeneration {
    public static void generateTrees() {
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED.unwrapKey().get());
//        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED.unwrapKey().get());
//        BiomeModifications.addFeature(BiomeSelectors.tag(HibiscusTags.Biomes.IS_WISTERIA),
//                GenerationStep.Decoration.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED.unwrapKey().get());
    }

    public static void addSugiVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.FOREST_ROCK);
    }

    public static void addRedwoodRock(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_ROCK_PLACED);
    }

    public static void addWisteriaTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.WISTERIA_PLACED);
    }

    public static void addSugiTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.SUGI_PLACED);
    }

    public static void addFirTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FIR_PLACED);
    }
    public static void addDenseFirTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.DENSE_FIR_PLACED);
    }

    public static void addAspenTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.ASPEN_PLACED);
    }
    public static void adFewAspenTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FEW_ASPEN_PLACED);
    }

    public static void addRedwoodTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.REDWOOD_PLACED);
    }

    public static void addLargeRedwoodTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.LARGE_REDWOOD_PLACED);
    }

    public static void addOliveTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.OLIVE_PLACED);
    }

    public static void addCypressTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.CYPRESS_PLACED);
    }

    public static void addOakTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.CUSTOM_FANCY_OAK_TREE_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
    }

    public static void addFewOakTrees(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.CUSTOM_FANCY_OAK_TREE2_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
    }

    public static void addSpruceBush(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.SPRUCE_BUSH_PLACED);
    }

    public static void addWisteriaFlowers(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_WISTERIA_PLACED);
    }

    public static void addSugiSecondaryVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_SUGI_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_SUGAR_CANE_SWAMP);
    }

    public static void addGoldenWildsVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_GOLDEN_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }

    public static void addGoldenFieldsVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_GOLDEN2_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }

    public static void addLavenderFlowers(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_LAVENDER_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }

    public static void addErodedRiverFlowers(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.OAK_BUSH_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_RIVER_PLACED);
    }

    public static void addRedwoodSecondaryVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_REDWOOD_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }
    public static void addFirVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_FIR_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }
    public static void addCypressVegetation(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, HibiscusPlacedFeatures.FLOWER_CYPRESS_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FOREST_FLOWERS);
    }
}
