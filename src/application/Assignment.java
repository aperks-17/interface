package application;

import java.util.Date;

public class Assignment {
		
		private String name;
		private String className;
		private String assignedDate;
		private String dueDate;
	
			
		public Assignment()
		{
			this.name = "";
			this.className = "";
			this.assignedDate = "";
			this.dueDate = "";
			
		}
		
		public Assignment (String name,String className,String assignedDate,String dueDate)
		{

			this.name = name;
			this.className = className;
			this.assignedDate = assignedDate;
			this.dueDate = dueDate;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getAssignedDate() {
			return assignedDate;
		}

		public void setAssignedDate(String assignedDate) {
			this.assignedDate = assignedDate;
		}

		public String getDueDate() {
			return dueDate;
		}

		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}
		
		
}
