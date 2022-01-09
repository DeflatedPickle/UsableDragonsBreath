/* Copyright (c) 2021-2022 DeflatedPickle under the MIT license */

package com.deflatedpickle.usabledragonsbreath

import net.fabricmc.api.ModInitializer
import net.minecraft.entity.projectile.DragonFireballEntity
import net.minecraft.item.ItemUsage
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.Items

@Suppress("UNUSED")
object UsableDragonsBreath : ModInitializer {
    private const val MOD_ID = "$[id]"
    private const val NAME = "$[name]"
    private const val GROUP = "$[group]"
    private const val AUTHOR = "$[author]"
    private const val VERSION = "$[version]"

    override fun onInitialize() {
        println(listOf(MOD_ID, NAME, GROUP, AUTHOR, VERSION))
    }

    fun useOnBlock(context: ItemUsageContext) {
        context.player?.let { player ->
            if (!player.world.isClient) {
                DragonFireballEntity(
                    player.world,
                    player,
                    context.blockPos.x.toDouble(),
                    context.blockPos.y.toDouble(),
                    context.blockPos.z.toDouble(),
                ).apply {
                    refreshPositionAndAngles(
                        context.blockPos,
                        0f, 0f,
                    )
                    world.spawnEntity(this)
                }

                if (!player.isCreative) {
                    val hand = player.activeHand
                    val stack = player.getStackInHand(hand)

                    player.setStackInHand(
                        hand,
                        ItemUsage.exchangeStack(
                            stack,
                            player,
                            Items.GLASS_BOTTLE.defaultStack
                        )
                    )
                }
            }
        }
    }
}
