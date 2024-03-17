package de.securess.uam.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.securess.uam.data.Role;
import de.securess.uam.services.UamService;

@PageTitle("Funktionen")
@Route(value = "roles")
public class ListRolesView extends VerticalLayout {

    private final UamService uamService;
    Grid<Role> grid = new Grid<>(Role.class);
    TextField filterText = new TextField();
    RoleForm roleForm;

    public ListRolesView(UamService uamService) {
        this.uamService = uamService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolBar(), getContent());
        updateList();
        closeEditor();
    }

    private void configureForm() {
        roleForm = new RoleForm(uamService.findAllUnits());
        roleForm.setWidth("100em");
        roleForm.addSaveListener(this::saveRole);
        roleForm.addDeleteListener(this::deleteRole);
        roleForm.addCloseListener(e -> closeEditor());
    }

    private void closeEditor() {
        roleForm.setRole(null);
        roleForm.setVisible(false);
        removeClassName("editing");
    }

    private void deleteRole(RoleForm.DeleteEvent deleteEvent) {
        uamService.deleteRole(deleteEvent.getRole());
        updateList();
        closeEditor();
    }

    private void saveRole(RoleForm.SaveEvent saveEvent) {
        uamService.saveRole(saveEvent.getRole());
        updateList();
        closeEditor();
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, roleForm);
        content.setFlexGrow(1, grid);
        content.setFlexGrow(2, roleForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private Component  getToolBar() {
        filterText.setPlaceholder("Filter by role...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addRoleButton = new Button("Add role");
        addRoleButton.addClickListener(click -> addRole());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addRoleButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addRole() {
        grid.asSingleSelect().clear();
        editRole(new Role());
    }

    private void updateList() {
        grid.setItems(uamService.findAllRoles(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("uam-grid");
        grid.setSizeFull();
        grid.setColumns("title");
        grid.addColumn(role -> role.getUnit() != null ? role.getUnit().getName() : null).setHeader("Unit");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editRole(event.getValue()));
    }

    private void editRole(Role role) {
        if (role == null) {
            closeEditor();
        } else {
            roleForm.setRole(role);
            roleForm.setVisible(true);
            addClassName("editing");
        }
    }

}
