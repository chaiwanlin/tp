package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.PartOfModulePredicate.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.logic.commands.CdCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PartOfModulePredicate;

/**
 * Parses input arguments and creates a new CdCommand object
 */
public class CdCommandParser implements Parser<CdCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CdCommand
     * and returns a CdCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CdCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CdCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.equalsIgnoreCase("*")) {
            return new CdCommand(PREDICATE_SHOW_ALL_MODULES);
        }

        return new CdCommand(new PartOfModulePredicate(trimmedArgs));
    }

}
