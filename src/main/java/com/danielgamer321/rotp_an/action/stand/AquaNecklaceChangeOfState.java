package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.mob.StandUserDummyEntity;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.general.LazySupplier;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class AquaNecklaceChangeOfState extends StandEntityAction {
    public AquaNecklaceChangeOfState(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        if (!(user instanceof PlayerEntity || user instanceof StandUserDummyEntity)) {
            return ActionConditionResult.NEGATIVE;
        }
        return super.checkSpecificConditions(user, power, target);
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            boolean shift = isShiftVariation();
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            if (shift) {
                if (aqua.getState() != 0) {
                    aqua.setState(0);
                }
                else {
                    aqua.setState(2);
                }
            }
            else {
                if (aqua.getState() != 1) {
                    aqua.setState(1);
                }
                else {
                    aqua.setState(2);
                }
            }
            aqua.setNoGravity(aqua.standHasNoGravity());
        }
    }

    @Override
    public float getStaminaCost(IStandPower stand) {
        if (stand.isActive() && stand.getStandManifestation() instanceof StandEntity) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand.getStandManifestation();
            boolean shift = isShiftVariation();
            if ((aqua.getState() == 1 && shift) || (aqua.getState() == 0 && !shift)) {
                return super.getStaminaCost(stand) / 2;
            }
        }
        return super.getStaminaCost(stand);
    }

    @Override
    public void onMaxTraining(IStandPower power) {
        power.unlockAction((StandAction) getShiftVariationIfPresent());
    }

    @Override
    public String getTranslationKey(IStandPower power, ActionTarget target) {
        String key = super.getTranslationKey(power, target);
        if (getState(power)) {
            key += ".undo";
        }
        return key;
    }

    private final LazySupplier<ResourceLocation> undoTex =
            new LazySupplier<>(() -> makeIconVariant(this, "_undo"));
    @Override
    public ResourceLocation getIconTexturePath(@Nullable IStandPower power) {
        if (power != null && getState(power)) {
            return undoTex.get();
        }
        else {
            return super.getIconTexturePath(power);
        }
    }

    private boolean getState(IStandPower power) {
        if (power.isActive() && power.getStandManifestation() instanceof StandEntity) {
            boolean shift = isShiftVariation();
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) power.getStandManifestation();
            return (aqua.getState() == 1 && !shift) || (aqua.getState() == 0 && shift);
        }
        return false;
    }



    @Deprecated
    private ResourceLocation undoTexPath;
    @Deprecated
    @Override
    public ResourceLocation getTexture(IStandPower power) {
        ResourceLocation resLoc = getRegistryName();
        if (getState(power)) {
            if (undoTexPath == null) {
                undoTexPath = new ResourceLocation(resLoc.getNamespace(), resLoc.getPath() + "_undo");
            }
            resLoc = undoTexPath;
        }
        return resLoc;
    }

    @Deprecated
    @Override
    public Stream<ResourceLocation> getTexLocationstoLoad() {
        ResourceLocation resLoc = getRegistryName();
        return Stream.of(resLoc, new ResourceLocation(resLoc.getNamespace(), resLoc.getPath() + "_undo"));
    }
}
