package projeto.infrastructure;

import java.io.Serializable;

public interface IObjectPersistent<T> extends Serializable {

    T getId();
}
