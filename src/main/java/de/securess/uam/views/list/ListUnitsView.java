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
import de.securess.uam.data.Unit;
import de.securess.uam.services.UamService;

@PageTitle("Abteilungen")
@Route(value = "units")
public class ListUnitsView extends VerticalLayout {
    private final UamService uamService;
    Grid<Unit> grid = new Grid<>(Unit.class);
    TextField filterText = new TextField();
    UnitForm unitForm;


    public ListUnitsView(UamService uamService) {
        this.uamService = uamService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolBar(), getContent());
        updateList();
        closeEditor();

    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, unitForm);
        content.setFlexGrow(1, grid);
        content.setFlexGrow(2, unitForm);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private Component getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addUnitButton = new Button("Add unit");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addUnitButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(uamService.findAllUnits(filterText.getValue()));
    }

    private void configureForm() {
        unitForm = new UnitForm();
        unitForm.setWidth("100em");

    }

    private void configureGrid() {
        grid.addClassNames("uam-grid");
        grid.setSizeFull();
        grid.setColumns("name");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editUnit(event.getValue()));
    }

    private void editUnit(Unit value) {
        if (value == null) {
            closeEditor();
        } else {
            unitForm.setUnit(value);
            unitForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        unitForm.setUnit(null);
        unitForm.setVisible(false);
        removeClassName("editing");
    }
}
