package mrthomas20121.tfcompat.compat.horsepower;

import mrthomas20121.tfcompat.library.ModuleCore;
import net.dries007.tfc.util.Helpers;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class HorsePowerModule extends ModuleCore {

    private HorsePowerRecipes recipesModule = Helpers.getNull();

    public HorsePowerModule() {
        super("module_horsepower", "horsepower");
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        recipesModule = new HorsePowerRecipes();
    }

    @Override
    public void init(FMLInitializationEvent event) {
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public void initRecipes(IForgeRegistry<IRecipe> r) {
        recipesModule.registerRecipes(r);
    }
}
