package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.danielgamer321.rotp_an.init.InitStands;
import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.action.ActionConditionResult;
import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.action.stand.StandAction;
import com.github.standobyte.jojo.action.stand.StandEntityAction;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class AquaNecklaceGettingIntoTheEntity extends StandEntityAction {
    public AquaNecklaceGettingIntoTheEntity(Builder builder) {
        super(builder);
    }

    @Override
    protected Action<IStandPower> replaceAction(IStandPower power, ActionTarget target) {
        if (power.isActive() && power.getStandManifestation() instanceof StandEntity) {
            StandEntity stand = (StandEntity) power.getStandManifestation();
            if (stand instanceof AquaNecklaceEntity && ((AquaNecklaceEntity) stand).isInside()) {
                return InitStands.AQUA_NECKLACE_LEAVE_THE_ENTITY.get();
            }
        }
        return super.replaceAction(power, target);
    }

    @Override
    public StandAction[] getExtraUnlockable() {
        return new StandAction[] { InitStands.AQUA_NECKLACE_LEAVE_THE_ENTITY.get() };
    }

    @Override
    protected ActionConditionResult checkStandConditions(StandEntity stand, IStandPower power, ActionTarget target) {
        if (!(stand.getUser() instanceof PlayerEntity)) {
            return ActionConditionResult.NEGATIVE;
        }
        Entity targetEntity = target.getEntity();
        if (targetEntity.is(power.getUser()) || !(targetEntity instanceof LivingEntity) ||
                targetEntity instanceof StandEntity) {
            return ActionConditionResult.NEGATIVE;
        }
        if (stand instanceof AquaNecklaceEntity && ((AquaNecklaceEntity)stand).isInside()) {
            return ActionConditionResult.NEGATIVE;
        }
        return super.checkStandConditions(stand, power, target);
    }

    @Override
    public TargetRequirement getTargetRequirement() {
        return TargetRequirement.ENTITY;
    }

    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        if (!world.isClientSide()) {
            ActionTarget target = task.getTarget();
            LivingEntity livingTarget = (LivingEntity) target.getEntity();
            if (standEntity instanceof AquaNecklaceEntity){
                AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
                aqua.setTargetInside(livingTarget);
                InitStands.AQUA_NECKLACE_LEAVE_THE_ENTITY.get().setCooldownOnUse(userPower);
            }
        }
    }

    @Override
    public boolean cancelHeldOnGettingAttacked(IStandPower power, DamageSource dmgSource, float dmgAmount) {
        return true;
    }
}
