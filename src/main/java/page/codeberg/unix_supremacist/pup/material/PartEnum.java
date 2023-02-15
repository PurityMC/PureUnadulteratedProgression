package page.codeberg.unix_supremacist.pup.material;

import lombok.Getter;
import page.codeberg.unix_supremacist.pup.Tags;

import java.util.HashMap;

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
    ring;


    @Getter Part part;
    PartEnum(){
        HashMap<Integer, Material> mats = new HashMap<>();
        for (MaterialEnum mat : MaterialEnum.values()) if (mat.getMaterial().getParts().contains(this.name())) mats.put(mat.ordinal(), mat.material);
        part = new Part(Tags.MODID, this.name()).setMats(mats);
    }
}
