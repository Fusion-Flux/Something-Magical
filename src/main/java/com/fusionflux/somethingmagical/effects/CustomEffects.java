package com.fusionflux.somethingmagical.effects;

import com.fusionflux.somethingmagical.SomethingMagical;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomEffects {

    public static final StatusEffect DRAGONHEALING = new DragonHealing(StatusEffectType.BENEFICIAL,0x98D982);

    public static void registerEffects() {

        Registry.register(Registry.STATUS_EFFECT,  new Identifier(SomethingMagical.MOD_ID, "dragon_healing"), DRAGONHEALING);
    }
}
