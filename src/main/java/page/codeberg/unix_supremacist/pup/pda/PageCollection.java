package page.codeberg.unix_supremacist.pup.pda;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;

import page.codeberg.unix_supremacist.pup.pda.pages.BasePage;

import com.google.common.collect.Lists;

public class PageCollection extends Gui {

    public final List<BasePage> pages = Lists.newArrayList();
    private String ACTIVE_PAGE = "CONTENTS";
    protected int x;
    protected int y;

    public PageCollection() {
        this.x = 0;
        this.y = 0;
    }

    public void addPage(BasePage page) {
        pages.add(page);
    }

    public BasePage getPageByName(String name) {
        for (BasePage component : pages) {
            if (component.getReferenceName().equals(ACTIVE_PAGE)) {
                return component;
            }
        }
        return null;
    }

    public BasePage getActivePage() {
        for (BasePage component : pages) {
            if (component.getReferenceName().equals(ACTIVE_PAGE)) {
                return component;
            }
        }
        return null;
    }

    public final void drawScreen(Minecraft minecraft, int offsetX, int offsetY, int mouseX, int mouseY) {
        if (getActivePage() == null) return;
        getActivePage().drawScreen(minecraft, offsetX, offsetY, mouseX, mouseY);
    }

    public final void renderBackgroundLayer(Minecraft minecraft, int offsetX, int offsetY, int mouseX, int mouseY) {
        if (getActivePage() == null) return;
        getActivePage().renderBackgroundLayer(minecraft, offsetX, offsetY, mouseX, mouseY);
    }

    public void changeActivePage(String newPage) {
        ACTIVE_PAGE = newPage;
        if (getActivePage() == null) return;
        getActivePage().setWorldAndResolution(Minecraft.getMinecraft(), x, y);
    }

    public void setWorldAndResolution(Minecraft minecraft, int x, int y) {
        if (getActivePage() == null) return;
        getActivePage().setWorldAndResolution(minecraft, x, y);
        this.x = x;
        this.y = y;
    }

    protected void actionPerformed(GuiButton button) {
        if (getActivePage() == null) return;
        getActivePage().actionPerformed(button);
    }

    protected void mouseMovedOrUp(int par1, int par2, int par3) {
        if (getActivePage() == null) return;
        getActivePage().mouseMovedOrUp(par1, par2, par3);
    }

    protected void mouseClicked(int par1, int par2, int par3) {
        if (getActivePage() == null) return;
        getActivePage().mouseClicked(par1, par2, par3);
    }

}
