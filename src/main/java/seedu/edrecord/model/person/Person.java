package seedu.edrecord.model.person;

import static seedu.edrecord.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.MaxScore;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.module.ModuleGroupMap;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.tag.Tag;

/**
 * Represents a Person in EdRecord.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Info info;
    private final ModuleGroupMap modules = new ModuleGroupMap();
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and cannot be null.
     */
    public Person(Name name, Phone phone, Email email, Info info, ModuleGroupMap modules, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, info, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.info = info;
        this.modules.addAll(modules);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Info getInfo() {
        return info;
    }

    public ModuleGroupMap getModules() {
        return this.modules;
    }

    // Dummy method. TODO integrate with Grades
    public MaxScore getGrade(Assignment assignment) {
        return new MaxScore("10");
    }

    public void addModuleClass(Module mod, Group group) {
        this.modules.add(mod, group);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getInfo().equals(getInfo())
                && otherPerson.getModules().equals(getModules())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, info, modules, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Info: ")
                .append(getInfo())
                .append("; Modules: ")
                .append(getModules());
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
