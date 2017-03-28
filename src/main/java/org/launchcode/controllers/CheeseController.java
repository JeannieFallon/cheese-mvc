package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.CheeseData;
import org.launchcode.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by jeannie on 3/8/17.
 */

@Controller
@RequestMapping("cheese")
public class CheeseController {



    @RequestMapping(value="")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String displayAddCheeseForm (Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddCheeseForm (@ModelAttribute @Valid Cheese newCheese,
                                        Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title","Add Cheese");
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }


    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String displayRemoveCheeseForm (Model model) {
        model.addAttribute("title", "Remove Cheese");
        model.addAttribute("cheeses", CheeseData.getAll());
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String processRemoveCheeseForm (@RequestParam int [] cheeseIds) {
        for(int cheeseId: cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese cheeseToBeEdited = CheeseData.getbyId(cheeseId);
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("cheese", cheeseToBeEdited);
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/edit";
    }

    @RequestMapping(value="edit/{cheeseId}", method=RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, String name, String description,
                                  CheeseType cheeseType) {
        Cheese cheeseToBeEdited = CheeseData.getbyId(cheeseId);
        cheeseToBeEdited.setName(name);
        cheeseToBeEdited.setDescription(description);
        cheeseToBeEdited.setType(cheeseType);
        // I don't understand what to return to get to home page. "redirect:" doesn't work
        return "redirect:/cheese";
    }

    /*  error in processEditForm when using return "redirect:"
        There was an unexpected error (type=Not Found, status=404).
        No message available
        ?

        >>> does "type=Not Found" refer to the 404 not found,
        or to the cheese type not found?
     */


}
