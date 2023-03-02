package page.codeberg.unix_supremacist.pup.material;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.commons.lang3.StringUtils;

import page.codeberg.unix_supremacist.pup.Tags;

public enum PartEnum {

    ingot,
    nugget,
    plate,
    gear,
    casing,
    hull,
    dust,
    tiny_dust,
    small_dust,
    hammer_head,
    bolt,
    rod,
    raw_ore,
    ring,
    gem,
    chip,
    tube;

    @Getter
    Part part;

    PartEnum() {
        HashMap<Integer, Material> mats = new HashMap<>();
        for (MaterialEnum mat : MaterialEnum.values())
            if (mat.getMaterial().getParts().contains(this.name())) mats.put(mat.ordinal(), mat.material);
        part = new Part(Tags.MODID, this.name()).setMats(mats);
        for (Map.Entry<Integer, Material> mat : mats.entrySet()) OreDictionary.registerOre(
                this.name() + StringUtils.capitalize(mat.getValue().getName().toLowerCase()),
                new ItemStack(part, 1, mat.getKey()));
    }
}
