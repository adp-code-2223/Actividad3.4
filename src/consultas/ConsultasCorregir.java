package consultas;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Account;
import modelo.Emp;
import util.SessionFactoryUtil;

public class ConsultasCorregir {

	public static void main(String[] args) {
		SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		{
			System.out.println("----------- Nombres de los de Departamentos-----------");
			List<String> deptList = session.createQuery(" select d.dname FROM Departamento d ").list();

			for (String nombre : deptList) {
				System.out.println("Nombre: " + nombre);
			}
		}

		{
			System.out.println("----------- Nombres de los empleados cuyo nombre comienza por M -----------");
			List<String> empList = session.createQuery("Select e.ename from Emp e where e.ename like 'M%'").list();

			for (String nombre : empList) {
				System.out.println("Nombre: " + nombre);
			}
		}

		{
			System.out.println("----------- Encontrar los empleados sin jefe -----------");
			List<Emp> empList = session.createQuery("Select e from Emp e where e.emp is null").list();

			for (Emp empleado : empList) {
				System.out.println("Empleado: " + empleado);
			}
		}

		{
			System.out.println("----------- Mostrar los ids de las cuentas sin movimientos-----------");
			List<Integer> cuentasIds = session.createQuery("Select a.accountno from Account a where"
					+ "(size(a.accMovements) =0) ")
					.list();

			for (Integer id : cuentasIds) {
				System.out.println("Id: " + id);
			}
		}

		session.close();
		sessionFactory.close();

	}

}