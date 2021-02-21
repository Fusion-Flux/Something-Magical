package com.fusionflux.somethingmagical;

import com.fusionflux.somethingmagical.blocks.SomethingMagicalBlocks;
import com.fusionflux.somethingmagical.effects.CustomEffects;
import net.fabricmc.api.ModInitializer;

public class SomethingMagical implements ModInitializer {

	public static final String MOD_ID = "somethingmagical";


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		SomethingMagicalBlocks.registerBlocks();
		CustomEffects.registerEffects();
		System.out.println("Hello Fabric world!");
	}
}
