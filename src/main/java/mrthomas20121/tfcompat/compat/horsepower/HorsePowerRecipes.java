package mrthomas20121.tfcompat.compat.horsepower;

import mrthomas20121.tfcompat.library.recipes.RecipeCore;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.wood.BlockLogTFC;
import net.dries007.tfc.objects.blocks.wood.BlockPlanksTFC;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import se.gory_moon.horsepower.recipes.HPRecipes;

public class HorsePowerRecipes implements RecipeCore {

    @Override
    public void registerRecipes(IForgeRegistry<IRecipe> r) {
        createChoppingBlockRecipes();
        createGrindstoneRecipes();
    }

    private void createChoppingBlockRecipes() {
        for(Tree tree : TFCRegistries.TREES.getValuesCollection()) {
            HPRecipes.instance()
                    .addChoppingRecipe(BlockLogTFC.get(tree), new ItemStack(BlockPlanksTFC.get(tree), 2), 8, true);
        }
    }

    private void createGrindstoneRecipes() {
        for(QuernRecipe recipe: TFCRegistries.QUERN.getValuesCollection()) {
            ItemStack input = recipe.getIngredients()
                    .get(0)
                    .getValidIngredients()
                    .get(0);
            ItemStack output = recipe.getOutputs()
                    .get(0);
            HPRecipes.instance().addChoppingRecipe(input, output, 8, true);
        }
    }
}
