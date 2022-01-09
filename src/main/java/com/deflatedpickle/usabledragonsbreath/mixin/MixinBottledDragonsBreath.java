package com.deflatedpickle.usabledragonsbreath.mixin;

import com.deflatedpickle.usabledragonsbreath.UsableDragonsBreath;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@SuppressWarnings({"UnusedMixin", "unused"})
@Mixin(Item.class)
abstract public class MixinBottledDragonsBreath {
    @Shadow private @Nullable String translationKey;

    @Inject(
            method = "useOnBlock",
            at = @At("RETURN")
    )
    public void onUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (!Objects.equals(Registry.ITEM.getId(context.getStack().getItem()).toString(), "minecraft:dragon_breath")) return;
        UsableDragonsBreath.INSTANCE.useOnBlock(context);
    }
}
