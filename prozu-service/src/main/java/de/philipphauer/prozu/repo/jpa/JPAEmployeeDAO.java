package de.philipphauer.prozu.repo.jpa;
//package de.itemis.prozu.repo.jpa;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import com.mysema.query.QueryModifiers;
//import com.mysema.query.jpa.impl.JPADeleteClause;
//import com.mysema.query.jpa.impl.JPAQuery;
//import com.mysema.query.jpa.impl.JPAUpdateClause;
//
//import de.itemis.prozu.model.Employee;
//import de.itemis.prozu.model.ProjectDay;
//import de.itemis.prozu.model.QEmployee;
//import de.itemis.prozu.model.QProjectDays;
//import de.itemis.prozu.repo.EmployeeDAO;
//import de.itemis.prozu.repo.RepositoryException;
//
//@Stateless
//public class JPAEmployeeDAO implements EmployeeDAO {
//
//	private static final QEmployee e = new QEmployee("employee");
//	private static final QProjectDays p = new QProjectDays("projectDay");
//
//	// ignore ERROR during startup:
//	// http://stackoverflow.com/questions/12054422/unsuccessful-alter-table-xxx-drop-constraint-yyy-in-hibernate-jpa-hsqldb-standa
//
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public List<Employee> getAllEmployees() {
//		List<Employee> employees = db().from(e).list(e);
//		return employees;
//	}
//
//	@Override
//	public List<Employee> getEmployees(int limit, int offset, Optional<String> search) {
//		JPAQuery query = db().from(e)
//				.restrict(new QueryModifiers((long) limit, (long) offset));
//		if (search.isPresent()) {
//			query = query.where(e.name.containsIgnoreCase(search.get()));
//		}
//		List<Employee> employees = query.list(e);
//		return employees;
//	}
//
//	@Override
//	public Optional<Employee> getEmployee(long employeeId) {
//		Employee employee = db().from(e).where(e.id.eq(employeeId))
//				.uniqueResult(e);
//		return Optional.ofNullable(employee);
//	}
//
//	@Override
//	public List<ProjectDay> getAllProjectDayes(long employeeId) {
//		List<ProjectDay> ProjectDayes = db().from(p)
//				.where(p.employee.id.eq(employeeId)).list(p);
//		return ProjectDayes;
//	}
//
//	@Override
//	public Employee createEmployee(String name) {
//		Employee newEmployee = new Employee(name);
//		entityManager.persist(newEmployee);
//		entityManager.flush();// get id in entity
//		return newEmployee;
//	}
//
//	@Override
//	public void updateEmployee(long id, String name) {
//		new JPAUpdateClause(entityManager, e).where(e.id.eq(id))
//				.set(e.name, name).execute();
//	}
//
//	@Override
//	public void save(Object entity) {
//		entityManager.persist(entity);
//		// entityManager.flush();// get id in entity
//	}
//
//	@Override
//	public void saveAll(List<? extends Object> entities) {
//		for (Object entity : entities) {
//			entityManager.persist(entity);
//		}
//		// entityManager.flush();// get id in entity
//	}
//
//	@Override
//	public void deleteEmployee(long employeeId) {
//		// cascade delete somehow doesn't work because of bidirectinal references...
//		// --> delete all ProjectDayes first
//		new JPADeleteClause(entityManager, p)
//				.where(p.employee.id.eq(employeeId)).execute();
//		long amountOfDeletedEntities = new JPADeleteClause(entityManager, e)
//				.where(e.id.eq(employeeId)).execute();
//		if (amountOfDeletedEntities == 0) {
//			throw new RepositoryException("Nothing to delete");
//		}
//	}
//
//	private JPAQuery db() {
//		JPAQuery db = new JPAQuery(entityManager);
//		return db;
//	}
//
//	@Override
//	public long getEmployeeCount(Optional<String> search) {
//		JPAQuery query = db().from(e);
//		if (search.isPresent()) {
//			query = query.where(e.name.containsIgnoreCase(search.get()));
//		}
//		return query.count();
//	}
//
// }
