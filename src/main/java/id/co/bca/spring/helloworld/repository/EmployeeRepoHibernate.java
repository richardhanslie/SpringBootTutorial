package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.EmployeeModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Repository
public class EmployeeRepoHibernate implements IEmployeeRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Override
    @Transactional
    public void create(EmployeeModel employee) {
        //EntityManager entityManager = entityManagerFactory.createEntityManager();
        //entityManager.getTransaction().begin();
        entityManager.persist(employee);
        //entityManager.getTransaction().commit();
    }

    @Override
    public List<EmployeeModel> retrieveAll() {
        return entityManager.createQuery("from EmployeeModel", EmployeeModel.class).getResultList();
    }

    @Override
    public EmployeeModel retrieveUnique(EmployeeModel employee) {
        return entityManager.find(EmployeeModel.class, employee.getId());
    }

    @Override
    public void update(EmployeeModel employee) {
        transactionTemplate.execute(new TransactionCallback<EmployeeModel>() {
            @Override
            public EmployeeModel doInTransaction(TransactionStatus status) {
                EmployeeModel dbEmployee = entityManager.find(EmployeeModel.class, employee.getId());
                dbEmployee.setFirstName(employee.getFirstName());
                dbEmployee.setLastName(employee.getLastName());
                dbEmployee.setEmail(employee.getEmail());
                return null;
            }
        });
    }

    @Override
    public void deleteUnique(EmployeeModel employee) {
        transactionTemplate.execute(new TransactionCallback<EmployeeModel>() {
            @Override
            public EmployeeModel doInTransaction(TransactionStatus status) {
                Query query = entityManager.createQuery("delete from EmployeeModel where id=:id");
                query.setParameter("id", employee.getId());
                query.executeUpdate();
                return null;
            }
        });
    }
}
