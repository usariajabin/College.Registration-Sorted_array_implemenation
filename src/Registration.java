import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Registration {

	public static void main(String[] args) {

		ArrayList<Student> roster = new ArrayList<Student>();

		Student[] sArray = new Student[15];

		// runtime for this for loop is n
		for (int i = 0; i < 15; i++) {
			sArray[i] = new Student();
		}

		SortedArray obj = new SortedArray();

		File file = new File("WarmUpData.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();
			StringTokenizer tokenizer;

			while (line != null) {
				Student s = new Student();
				tokenizer = new StringTokenizer(line, ",");

				String token = tokenizer.nextToken();
				s.setLastName(token);

				token = tokenizer.nextToken();
				s.setFirstName(token);

				token = tokenizer.nextToken();
				s.setId(token);

				line = br.readLine();
				tokenizer = new StringTokenizer(line, ",");
				token = tokenizer.nextToken();

				double currentCredits = 0;
				double tempCredit = 0;
				double currentGpa = 0;

				while (token.compareTo("-999") != 0) {

					Course c = new Course();

					c.setCourseNumber(token);

					token = tokenizer.nextToken();

					tempCredit = Double.parseDouble(token);
					c.setNumberOfCredit(tempCredit);

					currentCredits += Double.parseDouble(token);

					token = tokenizer.nextToken();
					c.setGradeRecieved(token);
					c.setGradePoint(c.gradeCount(token));
					currentGpa += tempCredit * c.gradeCount(token);

					line = br.readLine();
					tokenizer = new StringTokenizer(line, ",");
					token = tokenizer.nextToken();

					tempCredit = 0;
					s.courseList.add(c);
				}
				currentGpa = Math.round((currentGpa / currentCredits) * 100.0) / 100.0; // converting
																						// it
																						// to
																						// 2
																						// decimal
																						// place

				s.setTotalCredits(currentCredits);
				s.setGPA(currentGpa);
				// roster.add(s);

				sArray = obj.add(sArray, s);

				line = br.readLine();
				line = br.readLine();

				// System.out.println(s.courseList);

			} // done with reading from text file

			// adding students name manually

			Scanner input = new Scanner(System.in);

			System.out.println("\nSelect from the following menu");
			System.out.println("\tPress 1 : To add a student. ");
			System.out.println("\tPress 2 : To delete a student.");
			System.out.println("\tPress 3 : To search for a specific students info.");
			System.out.println("\tPress 4 : Show all the students info.");
			System.out.println("\tPress 5 : To add a course from a specific student's courselist.");
			System.out.println("\tPress 6 : To delete a course from a specific student's courselist.");

			System.out.println("\tPress 9 : To exit the program.");
			System.out.println();
			System.out.print("Enter your response: ");

			int answer = input.nextInt();
			Scanner input2 = new Scanner(System.in);
			String str;
			while (answer != 9) {
				Student s1 = new Student();

				if (answer == 1) {

					// Student s1 = new Student();

					int count1;

					// Scanner input2 = new Scanner(System.in);

					System.out.print("What is the last name  :");
					str = input2.next();
					s1.setLastName(str);

					System.out.print("What is the first name :");
					str = input2.next();
					s1.setFirstName(str);

					System.out.print("How many course did this student take?");
					count1 = Integer.parseInt(input2.next());

					// adding coursess for this student
					for (int k = 0; k < count1; k++) {
						Course c1 = new Course();

						System.out.println("Enter the course id :");
						str = input2.next();
						c1.setCourseNumber(str);

						System.out.println("What is the number of credits of this course? :");
						str = input2.next();

						c1.setNumberOfCredit(Double.parseDouble(str));

						System.out.println("What is the letter grade student recieved? :");
						str = input2.next();
						c1.setGradeRecieved(str);

						c1.setGradePoint(c1.gradeCount(str));

						s1.courseList.add(c1);

					}

					double tempCredits = 0;
					double tempGpa = 0;

					for (int i = 0; i < s1.courseList.size(); i++) {
						tempCredits += s1.courseList.get(i).getNumberOfCredits();
						tempGpa += s1.courseList.get(i).getGradePoint() * s1.courseList.get(i).getNumberOfCredits();

					}
					// currentGpa=Math.round((currentGpa/currentCredits)*100.0)/100.0;
					tempGpa = Math.round((tempGpa / tempCredits) * 100.0) / 100.0;
					s1.setGPA(tempGpa);
					s1.setTotalCredits(tempCredits);

					roster.add(s1);
					sArray = obj.add(sArray, s1);

					System.out.println("The student has been added. Here's the updated registration database");

					obj.print(sArray);
					System.out.println();
				}

				// deleting a student from the database
				else if (answer == 2) {
					System.out.print("Please enter the last name of the student :");
					str = input2.next();
					sArray = obj.delete(sArray, str);

					System.out.println("The student has been deleted. Here's the updated registration database");

					obj.print(sArray);
					System.out.println();

				}

				else if (answer == 3) {
					System.out.print("Please enter the last name of the student :");
					str = input2.next();
					// obj.search(sArray, str);
					// int integer=obj.search(sArray, str);
					int index = obj.search(sArray, str);

					if (index == -1)
						System.out.println("Student isn't in the database. ");

					else {

						System.out.print("Student's name & id : ");
						System.out.print(sArray[index].getLastName() + ", " + sArray[index].getFirstName());

						if (sArray[index].getId() != null) {
							System.out.println(", " + sArray[index].getId());

						} else
							System.out.println(", " + sArray[index].getgeneratedId());

						System.out.println("Total credits       : " + sArray[index].getTotalCredits());
						System.out.println("Gpa (calculated)    : " + sArray[index].getGPA());

						System.out.println("Course info :");

						for (int j = 0; j < sArray[index].courseList.size(); j++) {

							System.out.println(sArray[index].courseList.get(j).getCourseNumber() + "	 "
									+ sArray[index].courseList.get(j).getGradeRecieved());
						}

					}

				} else if (answer == 4) {

					for (int k = 0; k < sArray.length; k++) {
						if (sArray[k] != null && sArray[k].getLastName() != null) {
							System.out.print("Student's name & id : ");
							System.out.print(sArray[k].getLastName() + ", " + sArray[k].getFirstName());

							if (sArray[k].getId() != null) {
								System.out.println(", " + sArray[k].getId());

							} else
								System.out.println(", " + sArray[k].getgeneratedId());

							System.out.println("Total credits       : " + sArray[k].getTotalCredits());
							System.out.println("Gpa (calculated)    : " + sArray[k].getGPA());

							System.out.println("Course info :");

							for (int j = 0; j < sArray[k].courseList.size(); j++) {

								System.out.println(sArray[k].courseList.get(j).getCourseNumber() + "	 "
										+ sArray[k].courseList.get(j).getGradeRecieved());
							}
							System.out.println();
						}
					}

				} else if (answer == 5) {
					System.out.print("Please enter the last name of the student :");
					str = input2.next();

					int position = obj.search(sArray, str);

					if (position == -1) {
						System.out.println("Student is not in the database");
					} 
					else {
						Course c1 = new Course();

						System.out.println("Enter the course id :");
						str = input2.next();
						c1.setCourseNumber(str);

						System.out.println("What is the number of credits of this course? :");
						str = input2.next();

						c1.setNumberOfCredit(Double.parseDouble(str));

						System.out.println("What is the letter grade student recieved? :");
						str = input2.next();
						c1.setGradeRecieved(str);
						c1.setGradePoint(c1.gradeCount(str));

						sArray[position].courseList.add(c1);

						double tempCredits = 0;
						double tempGpa = 0;

						for (int i = 0; i < sArray[position].courseList.size(); i++) {
							tempCredits += sArray[position].courseList.get(i).getNumberOfCredits();
							tempGpa += sArray[position].courseList.get(i).getGradePoint()
									* sArray[position].courseList.get(i).getNumberOfCredits();

						}

						tempGpa = Math.round((tempGpa / tempCredits) * 100.0) / 100.0;
						sArray[position].setGPA(tempGpa);
						sArray[position].setTotalCredits(tempCredits);

						System.out.println("The course has been added. Here's the updated registration database");

						obj.print(sArray);
						System.out.println();
					}
				} 
				else if (answer == 6) {
					System.out.print("Please enter the last name of the student :");
					str = input2.next();

					int position = obj.search(sArray, str);

					if (position == -1) {
						System.out.println("Student is not in the database");
					}

					else {
						Course c1 = new Course();

						System.out.println("Enter the course id that you want to delete :");
						str = input2.next();

						for (int i = 0; i < sArray[position].courseList.size(); i++) {
							if (sArray[position].courseList.get(i).getCourseNumber().compareTo(str) == 0)
								sArray[position].courseList.remove(i);

						}
						System.out.println("The course has been deleted. Here's the updated registration database");

						obj.print(sArray);
						System.out.println();
					}
				}

				System.out.println("\nSelect from the following menu");
				System.out.println("\tPress 1 : To add a student. ");
				System.out.println("\tPress 2 : To delete a student.");
				System.out.println("\tPress 3 : To search for a specific students info.");
				System.out.println("\tPress 4 : Show all the students info.");
				System.out.println("\tPress 5 : To add a course from a specific student's courselist.");
				System.out.println("\tPress 6 : To delete a course from a specific student's courselist.");

				System.out.println("\tPress 9 : To exit the program.");
				System.out.println();
				System.out.print("Enter your response: ");

				answer = input.nextInt();

			}

		}

		catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	} // end of main

}
