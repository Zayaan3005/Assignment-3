getAllStudents(): Displays all the student records from the table

addStudent(first_name, last_name, email, enrollment_date): Adds a student record with the given parameters

updateStudentEmail(student_id, new_email): Changes the student email to the provided new_email with the provided student_id

deleteStudent(student_id) – Deletes a student record with the given student_id



We have to run the 'db/database_setup.sql' file to create the table and insert the data, then we can compile and run the application

To compile the application: 	javac -cp ".;postgresql-42.7.8.jar" Main.java
To run the application: 	java -cp ".;postgresql-42.7.8.jar" Main



Link to my Video: https://youtu.be/xUDRenJqN6g

