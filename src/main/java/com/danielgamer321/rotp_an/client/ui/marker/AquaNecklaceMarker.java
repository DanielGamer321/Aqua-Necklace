package com.danielgamer321.rotp_an.client.ui.marker;

import com.danielgamer321.rotp_an.RotpAquaAddon;
import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.danielgamer321.rotp_an.init.AddonStands;
import com.github.standobyte.jojo.client.ui.marker.MarkerRenderer;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;


public class AquaNecklaceMarker extends MarkerRenderer {

    public AquaNecklaceMarker(Minecraft mc) {
        super(AddonStands.AQUA_NECKLACE.getStandType().getColor(), new ResourceLocation(RotpAquaAddon.MOD_ID, "textures/power/aqua_necklace.png"), mc);
    }
    @Override
    protected boolean shouldRender() {
        return true;
    }
    @Override
    protected void updatePositions(List<MarkerInstance> list, float partialTick) {
        if (mc.player != null) {
            IStandPower.getStandPowerOptional(mc.player).ifPresent(stand -> {
                if (stand.getStandManifestation() instanceof AquaNecklaceEntity) {
                    AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand.getStandManifestation();
                    double distance = aqua.distanceToSqr(mc.player);
                    if (aqua.isInside() && (aqua.hasEffect(ModStatusEffects.FULL_INVISIBILITY.get()) || distance >= 100)){
                        LivingEntity target = aqua.getTargetInside();
                        Vector3d targetPos = target.getPosition(partialTick).add(0, target.getBbHeight() * 1.1, 0);
                        list.add(new MarkerInstance(targetPos, false));
                    }
                }
            });
        }
    }
}

