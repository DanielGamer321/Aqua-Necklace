package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityHeavyAttack;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.horse.SkeletonHorseEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AquaNecklaceHeavyPunch extends StandEntityHeavyAttack {

    public AquaNecklaceHeavyPunch(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        StandEntity stand = power.isActive() ? (StandEntity) power.getStandManifestation() : null;
        if (power.isActive() && stand instanceof AquaNecklaceEntity) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand;
            if (!aqua.isInside()) {
                return ActionConditionResult.NEGATIVE;
            }
            else {
                if (isASkeleton(aqua.getTargetInside()) && (aqua.getTargetInside() instanceof GolemEntity))
                    return ActionConditionResult.NEGATIVE;
                return super.checkSpecificConditions(user, power, target);
            }
        }
        return ActionConditionResult.NEGATIVE;
    }

    @Override
    public StandEntityHeavyAttack getFinisherVariationIfPresent(IStandPower power, @Nullable StandEntity standEntity) {
        if (standEntity instanceof AquaNecklaceEntity) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            if (aqua.isInside() && hasABrain(aqua)) {
                return super.getFinisherVariationIfPresent(power, standEntity);
            }
        }
        return this;
    }

    public static boolean hasABrain(AquaNecklaceEntity aqua) {
        return !isASkeleton(aqua.getTargetInside()) && !(aqua.getTargetInside() instanceof GolemEntity);
    }

    public static boolean isASkeleton(LivingEntity entity) {
        return entity instanceof AbstractSkeletonEntity || entity instanceof SkeletonHorseEntity ||
                entity instanceof WitherEntity;
    }

    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide()) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            if (aqua.getState() != 2) {
                aqua.setState(2);
                aqua.setNoGravity(aqua.standHasNoGravity());
                IStandPower.getStandPowerOptional(aqua.getUser()).ifPresent(stand -> {
                    stand.consumeStamina(aqua.getState() == 0 ? 80 : 40);
                });
            }
            if (aqua.getTargetInside() != null) {
                ActionTarget target = new ActionTarget(aqua.getTargetInside());
                aqua.setTaskTarget(target);
            }
        }
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        double strength = stand.getAttackDamage() * 0.75;
        if (stand instanceof AquaNecklaceEntity && ((AquaNecklaceEntity)stand).isInside()) {
            dmgSource.bypassArmor();
            strength = 0;
        }
        return super.punchEntity(stand, target, dmgSource)
                .addKnockback(0.5F + (float) strength / (8 - stand.getLastHeavyFinisherValue() * 4));
    }
}
