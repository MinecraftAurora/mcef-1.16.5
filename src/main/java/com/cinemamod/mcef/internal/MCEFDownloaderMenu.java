/*
 *     MCEF (Minecraft Chromium Embedded Framework)
 *     Copyright (C) 2023 CinemaMod Group
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 */

package com.cinemamod.mcef.internal;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;

public class MCEFDownloaderMenu extends Screen {
    private final MainMenuScreen menu;

    public MCEFDownloaderMenu(MainMenuScreen menu) {
        super(new StringTextComponent("MCEF is downloading required libraries..."));
        this.menu = menu;
    }

    @Override
    public void render(@Nonnull MatrixStack ms, int mouseX, int mouseY, float partialTick) {
        renderBackground(ms);
        double cx = width / 2d;
        double cy = height / 2d;

        double progressBarHeight = 14;
        double progressBarWidth = width / 3d; // TODO: base off screen with (1/3 of screen)

        /* Draw Progress Bar */
        ms.pushPose();
        ms.translate(cx, cy, 0);
        ms.translate(-progressBarWidth / 2d, -progressBarHeight / 2d, 0);
        AbstractGui.fill( // bar border
                ms,
                0, 0,
                (int) progressBarWidth,
                (int) progressBarHeight,
                -1
        );
        AbstractGui.fill( // bar padding
                ms,
                2, 2,
                (int) progressBarWidth - 2,
                (int) progressBarHeight - 2,
                -16777215
        );
        AbstractGui.fill( // bar bar
                ms,
                4, 4,
                (int) ((progressBarWidth - 4) * MCEFDownloadListener.INSTANCE.getProgress()),
                (int) progressBarHeight - 4,
                -1
        );
        ms.popPose();

        // putting this here incase I want to re-add a third line later on
        // allows me to generalize the code to not care about line count
        String[] text = new String[]{
                MCEFDownloadListener.INSTANCE.getTask(),
                Math.round(MCEFDownloadListener.INSTANCE.getProgress() * 100) + "%",
        };

        /* Draw Text */
        // calculate offset for the top line
        int oSet = ((font.lineHeight / 2) + ((font.lineHeight + 2) * (text.length + 2))) + 4;
        ms.pushPose();
        ms.translate(
                (int) (cx),
                (int) (cy - oSet),
                0
        );
        // draw menu name
        drawString(ms,
                font,
                new StringTextComponent(title.getString()).withStyle(TextFormatting.GOLD),
                (int) -(font.width(title.getString()) / 2d), 0,
                0xFFFFFF);
        // draw text
        int index = 0;
        for (String s : text) {
            if (index == 1) {
                ms.translate(0, font.lineHeight + 2, 0);
            }

            ms.translate(0, font.lineHeight + 2, 0);
            drawString(ms,
                    font,
                    s,
                    (int) -(font.width(s) / 2d), 0,
                    0xFFFFFF
            );
            index++;
        }
        ms.popPose();

        // TODO: if listener.isFailed(), draw some "Failed to initialize MCEF" text with an "OK" button to proceed
    }

    @Override
    public void tick() {
        if (MCEFDownloadListener.INSTANCE.isDone() || MCEFDownloadListener.INSTANCE.isFailed()) {
            onClose();
            Minecraft.getInstance().setScreen(menu);
        }
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}
