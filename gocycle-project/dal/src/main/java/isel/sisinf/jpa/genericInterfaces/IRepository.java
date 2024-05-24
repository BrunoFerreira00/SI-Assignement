package isel.sisinf.jpa.genericInterfaces;

public interface IRepository<T,TCol,TK> {
    T findByKey(TK key);
    TCol find(String jpql, Object... params);
}