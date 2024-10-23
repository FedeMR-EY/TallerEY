package ey.com.cuentas.mscuentas.service;

public interface JpaService<T> {
  public T findAll();

  public T save(T entity);

  public T findById(T id);
}
