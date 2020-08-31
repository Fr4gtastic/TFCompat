package mrthomas20121.tfcompat.recipes;

import cofh.thermalexpansion.util.managers.machine.RefineryManager;
import cofh.thermalexpansion.util.managers.machine.ExtruderManager;
import cofh.thermalexpansion.util.managers.machine.FurnaceManager;
import cofh.thermalexpansion.util.managers.machine.PrecipitatorManager;
import cofh.thermalexpansion.util.managers.machine.PulverizerManager;
import cofh.thermalexpansion.util.managers.machine.SawmillManager;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.wood.ItemLumberTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tfctech.objects.items.TechItems;

import java.util.ArrayList;

public class ThermalRecipes extends Recipes {
    public static void init()
    {
        RedstoneFurnaceRecipes();
        PulverizerRecipes();
        SawmillRecipes();
        PrecipiratorRecipes();
        ExtruderRecipes();
    }
    public static void postInit()
    {
        BarrelStillRecipes();
    }

    private static void BarrelStillRecipes()
    {
        RefineryManager.RefineryRecipe[] recipes = RefineryManager.getRecipeList();
        for(RefineryManager.RefineryRecipe recipe : recipes)
        {
            FluidStack input = recipe.getInput();
            FluidStack output = recipe.getOutputFluid();
            ItemStack outputItem = recipe.getOutputItem();
            TFCRegistries.BARREL.register(new BarrelRecipe(IIngredient.of(input), IIngredient.of(ItemStack.EMPTY), output, outputItem.isEmpty() ? ItemStack.EMPTY : outputItem, 61*ICalendar.TICKS_IN_DAY).setRegistryName("still_"+input.getFluid().getName()));
        }
    }
    private static void ExtruderRecipes()
    {
        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            ExtruderManager.addRecipeIgneous(1000, new ItemStack(BlockRockVariant.get(rock, Rock.Type.RAW)), FluidRegistry.getFluidStack("lava", 1000), FluidRegistry.getFluidStack("fresh_water", 1000));
            ExtruderManager.addRecipeSedimentary(1000, new ItemStack(BlockRockVariant.get(rock, Rock.Type.SAND)), FluidRegistry.getFluidStack("lava", 1000), FluidRegistry.getFluidStack("fresh_water", 1000));
        }
    }
    private static void PrecipiratorRecipes()
    {
        PrecipitatorManager.addRecipe(800, new ItemStack(Items.SNOWBALL, 4), getFluid("fresh_water", 500));
        PrecipitatorManager.addRecipe(800, new ItemStack(Blocks.SNOW, 1), getFluid("fresh_water", 500));
        PrecipitatorManager.addRecipe(800, new ItemStack(Blocks.SNOW_LAYER, 2), getFluid("fresh_water", 500));
        PrecipitatorManager.addRecipe(1600, new ItemStack(Blocks.ICE, 1), getFluid("fresh_water", 1000));
        PrecipitatorManager.addRecipe(1600, new ItemStack(Blocks.PACKED_ICE, 1), getFluid("fresh_water", 1000));
        PrecipitatorManager.addRecipe(1600, new ItemStack(BlocksTFC.SEA_ICE, 1), getFluid("salt_water", 1000));
    }
    private static void SawmillRecipes()
    {
        for(Tree tree : TFCRegistries.TREES.getValuesCollection())
        {
            SawmillManager.addRecipe(1000, getStack(BlockLogTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 8), getStack(TechItems.WOOD_POWDER, 1), 20);
            SawmillManager.addRecipe(1000,getStack(BlockPlanksTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 3), getStack(TechItems.WOOD_POWDER, 1), 20);
            SawmillManager.addRecipe(1000,getStack(BlockDoorTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 4), getStack(TechItems.WOOD_POWDER, 1), 20);
            SawmillManager.addRecipe(1000,getStack(BlockTrapDoorWoodTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 4), getStack(TechItems.WOOD_POWDER, 1), 20);
            SawmillManager.addRecipe(1000,getStack(BlockFenceTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 3), getStack(TechItems.WOOD_POWDER, 1), 20);
            SawmillManager.addRecipe(1000,getStack(BlockFenceGateTFC.get(tree), 1), getStack(ItemLumberTFC.get(tree), 4), getStack(TechItems.WOOD_POWDER, 1), 20);
        }
    }
    private static void RedstoneFurnaceRecipes()
    {
        for(HeatRecipe heatRecipe :TFCRegistries.HEAT.getValuesCollection())
        {
            NonNullList<IIngredient<ItemStack>> r = heatRecipe.getIngredients();
            if(r.size() > 0)
            {
                NonNullList<ItemStack> ingredient = r.get(0).getValidIngredients();
                FurnaceManager.addRecipe(1000, ingredient.get(0), heatRecipe.getOutputs().get(0));
            }
        }
    }
    private static void PulverizerRecipes()
    {
        PulverizerManager.PulverizerRecipe[] recipes = PulverizerManager.getRecipeList();
        for(PulverizerManager.PulverizerRecipe pulverizerRecipe: recipes)
        {
            PulverizerManager.removeRecipe(pulverizerRecipe.getInput());
        }
        for(QuernRecipe recipe : TFCRegistries.QUERN.getValuesCollection())
        {
            NonNullList<IIngredient<ItemStack>> ingredient = recipe.getIngredients();
            NonNullList<ItemStack> outputs = recipe.getOutputs();
            ItemStack output = outputs.get(0);
            NonNullList<ItemStack> stacks = ingredient.get(0).getValidIngredients();
            PulverizerManager.addRecipe(1000, stacks.get(0), output, output, 10);
        }
    }
}
