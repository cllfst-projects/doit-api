package com.doit.api.item;

import com.doit.api.project.Project;
import com.doit.api.project.ProjectRepository;
import com.doit.api.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, ProjectService projectService, ProjectRepository projectRepository) {
        this.itemRepository = itemRepository;
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    public String addItem(ItemRequest request, long projectId) {
        if (request == null) {
            return "Please insert Item";
        }
        boolean projectExists = projectRepository.findById(projectId).isPresent();
        if (!projectExists) {
            return "NO PROJECT";
        }
        Project project = projectService.getProject(projectId);
        Item item = Item.builder()
                .name(request.getName())
                .itemState(request.getItemState())
                .project(project)
                .build();
        boolean itemExists = projectService.itemExists(project, item.getName());
        if (itemExists) {
            return ("CONFLICT");
        }
        project.addItem(item);
        if (item.getName().length() > 100) {
            return "TOO LARGE";
        } else {
            itemRepository.save(item);
            projectRepository.save(project);
            return ("ADDED");
        }
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(long id) {
        Item item = getItems().stream()
                .filter(t -> id == t.getId())
                .findFirst()
                .orElse(null);
        return item;
    }

    public Item updateItemName(Item item) {
        Long id = item.getId();
        Item myItem = getItem(id);
        myItem.setName(item.getName());
        itemRepository.save(myItem);
        return (myItem);
    }

    public Item updateItemState(Item item) {
        Long id = item.getId();
        Item myItem = getItem(id);
        myItem.setItemState(item.getItemState());
        itemRepository.save(myItem);
        return (myItem);
    }


    public boolean deleteItem(long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists) {
            return false;
        }
        Item item = getItem(itemId);
        Project project = item.getProject();
        project.removeItem(item);
        projectRepository.save(project);
        itemRepository.deleteById(itemId);
        return true;
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }
}
