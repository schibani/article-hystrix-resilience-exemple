package fr.soat.hystrix;

import fr.soat.hystrix.model.RemoteCallException;
import fr.soat.hystrix.model.RemoteCallException.ExceptionType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher permettant de vérifier le tyde d'une RemoteCallException
 */
public class RemoteExceptionTypeMatcher extends TypeSafeMatcher<RemoteCallException> {

    final ExceptionType type;

    public RemoteExceptionTypeMatcher(ExceptionType type) {
        this.type = type;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expects type ").appendValue(type);
    }

    @Override
    protected void describeMismatchSafely(RemoteCallException exception, Description mismatchDescription) {
        mismatchDescription.appendText("was ").appendValue(exception.getExceptionType());
    }

    @Override
    protected boolean matchesSafely(RemoteCallException e) {
        return e.getExceptionType() == type;
    }

}
