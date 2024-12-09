
package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;

public class ClientDemo {
    public static void main(String[] args) {
        SessionFactory factory = (SessionFactory) ((Object) new Configuration());
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Insert Operation
            System.out.println("Inserting records...");
            Department dept1 = new Department();
            dept1.setName("Computer Science");
            dept1.setLocation("Building A");
            dept1.setHodName("Dr. Smith");
            session.persist(dept1);

            Department dept2 = new Department();
            dept2.setName("Mathematics");
            dept2.setLocation("Building B");
            dept2.setHodName("Dr. Johnson");
            session.persist(dept2);

            // Delete Operation using HQL with Positional Parameters
            System.out.println("Deleting a record...");
            @SuppressWarnings("deprecation")
			Query query = session.createQuery("DELETE FROM Department WHERE id = ?1");
            query.setParameter(1, 1); // Example: Deleting department with ID 1
            int rowsAffected = query.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
