package com.danielgamer321.rotp_an.action.stand;

import com.danielgamer321.rotp_an.entity.stand.stands.AquaNecklaceEntity;
import com.github.standobyte.jojo.action.stand.punch.StandEntityPunch;
import com.github.standobyte.jojo.entity.stand.StandEntity;
import com.github.standobyte.jojo.entity.stand.StandEntityTask;
import com.github.standobyte.jojo.entity.stand.StandStatFormulas;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.stand.IStandPower;
import com.github.standobyte.jojo.util.mc.damage.StandEntityDamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class AquaNecklaceBrainAttack extends AquaNecklaceHeavyPunch {

    public AquaNecklaceBrainAttack(Builder builder) {
        super(builder);
    }


    @Override
    public void standPerform(World world, StandEntity standEntity, IStandPower userPower, StandEntityTask task) {
        AquaNecklaceEntity aqua = (AquaNecklaceEntity) standEntity;
        if (aqua.isInside() && hasABrain(aqua)) {
            super.standPerform(world, standEntity, userPower, task);
        }
    }

    @Override
    public StandEntityPunch punchEntity(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
        AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand;
        double strength = stand.getAttackDamage() * 0.75;
        if (aqua.isInside()) {
            dmgSource.bypassArmor();
        }
        return new BrainAttackInstance(stand, target, dmgSource)
                .copyProperties(super.punchEntity(stand, target, dmgSource))
                .damage(StandStatFormulas.getHeavyAttackDamage(strength) * 2)
                .addKnockback(0)
                .reduceKnockback(0);
    }



    public static class BrainAttackInstance extends HeavyPunchInstance {

        public BrainAttackInstance(StandEntity stand, Entity target, StandEntityDamageSource dmgSource) {
            super(stand, target, dmgSource);
        }

        @Override
        protected void afterAttack(StandEntity stand, Entity target, StandEntityDamageSource dmgSource, StandEntityTask task, boolean hurt, boolean killed) {
            super.afterAttack(stand, target, dmgSource, task, hurt, killed);
            AquaNecklaceEntity aqua = (AquaNecklaceEntity) stand;
            if (hurt && !killed && target instanceof LivingEntity && aqua.getTargetInside() == target) {
                LivingEntity effectTarget = (LivingEntity) target.getEntity();
                effectTarget.addEffect(new EffectInstance(ModStatusEffects.STUN.get(), 60));
            }
        }
    }
}
