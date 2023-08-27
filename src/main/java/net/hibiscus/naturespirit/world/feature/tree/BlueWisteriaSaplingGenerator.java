package net.hibiscus.naturespirit.world.feature.tree;

import net.hibiscus.naturespirit.datagen.HibiscusConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class BlueWisteriaSaplingGenerator extends SaplingGenerator {

   @Override protected RegistryKey <ConfiguredFeature <?, ?>> getTreeFeature(Random randomSource, boolean bl) {
      return HibiscusConfiguredFeatures.BLUE_WISTERIA_TREE;
   }
}
