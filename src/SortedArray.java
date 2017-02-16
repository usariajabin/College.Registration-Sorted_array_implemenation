
public class SortedArray {

	public int search(Student[] sArr, String s) {
		int spot = -1;

		for (int i = 0; i < 15; i++) {

			if (sArr[i].getLastName() != null && sArr[i].getLastName().compareToIgnoreCase(s) == 0) {

				spot = i;
				
				return spot;
			}
		}
		return spot;
	}

	public Student[] add(Student[] sArr, Student s) {
		int spot = 0;
		
		if (sArr[0].getLastName() == null) {
			sArr[0] = s;
			return sArr;
		} 
		else {
			// finding the spot in my array
			int i = 0; 
			while (i < sArr.length) {
				if ((sArr[i].getLastName() != null) && ((sArr[i].getLastName().compareToIgnoreCase(s.getLastName()) > 0))) {
					break;
				} 
				if (sArr[i].getLastName() == null){
					break;
				}
				i++;
			}
			spot = i;

			int lastIndex = -1;
			for (int k = 0; k < sArr.length; k++) {
				if (sArr[k] == null)
					break;
				else
					lastIndex++;

			}

			if (lastIndex == sArr.length - 1) {
				Student[] sArrTemp = new Student[sArr.length * 2];

				for (int j = 0; j <= lastIndex; j++) {
					sArrTemp[j] = sArr[j];
				}
				sArr = sArrTemp;
			}
			
			for (int k = lastIndex; k >= spot; k--) {
				sArr[k + 1] = sArr[k];
			}
			sArr[spot] = s;

			return sArr;
		}
	}
	// end of add

	public Student[] delete(Student[] sArr, String s) {

		if (search(sArr, s) != -1) {

			int spot = search(sArr, s);
			for (int i = spot; i < sArr.length - 1; i++) {
				sArr[i] = sArr[i + 1];
			}
			return sArr;
		} else {
			System.out.println("Student isn't in the database. So nothing to delete");
			return sArr;
		}
	}
	
	public void print(Student [] sArray){
		
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
		
	}

}
