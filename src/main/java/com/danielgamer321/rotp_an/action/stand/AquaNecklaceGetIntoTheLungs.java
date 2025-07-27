package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.init.power.non_stand.ModPowers;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mod.JojoModUtil;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AquaNecklaceGetIntoTheLungs extends StandEntityAction {
    public AquaNecklaceGetIntoTheLungs(Builder builder) {
        super(builder);
    }

    @Override
    protected ActionConditionResult checkSpecificConditions(LivingEntity user, IStandPower power, ActionTarget target) {
        StandEntity stand = power.isActive() ? (StandEntity) power.getStandManifestation() : null;
        if (power.isActive() && stand instanceof AquaNecklaceEntity) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand;
            if (aqua.isInside() && needsToBreathe(aqua))
                return super.checkSpecificConditions(user, power, target);
        }
        return ActionConditionResult.NEGATIVE;
    }

    private boolean needsToBreathe(AquaNecklaceEntity aqua) {
        return !JojoModUtil.isAffectedByHamon(aqua.getTargetInside()) && !(aqua.getTargetInside() instanceof GolemEntity);
    }

    @Override
    public void onTaskSet(World world, StandEntity standEntity, IStandPower standPower, Phase phase, StandEntityTask task, int ticks) {
        super.onTaskSet(world, standEntity, standPower, phase, task, ticks);
        if (!world.isClientSide() && ((AquaNecklaceEntity)standEntity).getState() != 1) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            aqua.setState(1);
            aqua.setNoGravity(aqua.standHasNoGravity());
            IStandPower.getStandPowerOptional(aqua.getUser()).ifPresent(stand -> {
                stand.consumeStamina(40);
            });
        }
    }

    @Override
    public void standTickPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
            if (aqua.isInside() && aqua.getTargetInside() != null) {
                LivingEntity target = aqua.getTargetInside();
                if (target.isEyeInFluid(FluidTags.WATER) && !world.getBlockState(new BlockPos(target.getX(), target.getEyeY(), target.getZ())).is(Blocks.BUBBLE_COLUMN)) {
                    target.setAirSupply(target.getAirSupply() - 1);
                    if (target.getAirSupply() <= -20) {
                        target.setAirSupply(0);
                        target.hurt(DamageSource.DROWN, 2.0F);
                    }
                }
                else {
                    target.setAirSupply(target.getAirSupply() - 6);
                    if (INonStandPower.getNonStandPowerOptional(target)
                            .map(power -> power.getType() == ModPowers.HAMON.get()).orElse(false)) {
                        target.setAirSupply(target.getAirSupply() - 2);
                    }
                    if (target.getAirSupply() <= -20) {
                        target.setAirSupply(0);
                        target.hurt(DamageSource.DROWN, 2.0F);
                    }
                }
            }
        }
    }
}
