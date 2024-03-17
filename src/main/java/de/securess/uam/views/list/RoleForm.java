package de.securess.uam.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import de.securess.uam.data.Role;
import de.securess.uam.data.Unit;

import java.util.List;

public class RoleForm  extends FormLayout {
    TextField title = new TextField("Title");
    TextArea description = new TextArea("Description");
    TextArea targets = new TextArea("Targets");
    TextArea tasks = new TextArea("Tasks");
    TextArea responsibilities = new TextArea("Responsibilities");
    TextArea notes = new TextArea("Notes");
    DatePicker dateCreated = new DatePicker("Date Created");
    DatePicker dateApproved = new DatePicker("Date Approved");
    ComboBox<Unit> unit = new ComboBox<>("Unit");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Role> binder = new BeanValidationBinder<>(Role.class);

    public RoleForm(List<Unit> units) {
        addClassName("role-form");
        binder.bindInstanceFields(this);
        unit.setItems(units);
        unit.setItemLabelGenerator(Unit::getName);
        add(
                title,
                description,
                targets,
                tasks,
                responsibilities,
                notes,
                dateCreated,
                dateApproved,
                unit,
                createButtonsLayout()
        );
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave()); // <1>
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); // <2>
        close.addClickListener(event -> fireEvent(new CloseEvent(this))); // <3>

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public void setRole(Role role) {
        binder.setBean(role); // <1>
    }

    public static class SaveEvent extends RoleFormEvent{
        SaveEvent(RoleForm source, Role bean) {
            super(source, bean);
        }
    }

    public static class DeleteEvent extends RoleFormEvent {
        DeleteEvent(RoleForm source, Role role) {
            super(source, role);
        }
    }

    public static class CloseEvent extends RoleFormEvent {
        CloseEvent(RoleForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }

    public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
        return addListener(DeleteEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

    public static abstract class RoleFormEvent extends ComponentEvent<RoleForm> {
        private final Role role;

        protected RoleFormEvent(RoleForm source, Role role) {
            super(source, false);
            this.role = role;
        }

        public Role getRole() {
            return role;
        }
    }
}
