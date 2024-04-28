package com.studentManagement.service.excelService.impl;
import com.studentManagement.entity.Student;
import com.studentManagement.entity.Teacher;
import com.studentManagement.entity.User;
import com.studentManagement.entity.semester.Fees;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.TeacherRepository;
import com.studentManagement.repository.UserRepo;
import com.studentManagement.repository.semester.FeesInterface;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class StudentExcelFileServiceimpl
{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepo userrepository;

    @Autowired
    FeesInterface feerepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<Student> processExcelFile(MultipartFile file) throws IOException
    {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            List<Student> stdList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext())
            {
                rowIterator.next();
            }
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Student entity = new Student();
                User user=new User();
                populateEntityFromRow(entity,user,row);
                studentRepository.save(entity);
                userrepository.save(user);
            }
            return studentRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IOException(e.getMessage()+
                    "Error processing the Excel file.");
        }
    }

    private void populateEntityFromRow(Student entity,User user,Row row) throws IOException
    {
        String password=String.valueOf(getStringCellValue(row.getCell(9)));
        entity.setId(String.valueOf(getStringCellValue(row.getCell(0))));
        entity.setFirstName(String.valueOf(row.getCell(1)));
        entity.setLastName(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setEmail(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setTeacherId(String.valueOf(getStringCellValue(row.getCell(4))));
        entity.setDateofbirth(String.valueOf(getStringCellValue(row.getCell(5))));
        entity.setMobileno(String.valueOf(getStringCellValue(row.getCell(6))));
        entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(7))));
        entity.setDepartment(String.valueOf(getStringCellValue(row.getCell(6))));
        entity.setPassword(password);


        String role="ROLE_USER";
        user.setUserId(String.valueOf(getStringCellValue(row.getCell(0))));
        user.setRole(role);
        user.setName(String.valueOf(row.getCell(1))+String.valueOf(getStringCellValue(row.getCell(2))));
        user.setPassword(password);
    }
    private String getStringCellValue(Cell cell)
    {
        if (cell == null)
        {
            return null;
        }
        switch (cell.getCellType())
        {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Handle formula cells if needed
                return cell.getCellFormula();
        }
        return null;
    }

    public List<Teacher> processExcelFile1(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            List<Teacher> stdList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext())
            {
                rowIterator.next();
            }
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Teacher entity = new Teacher();
                User user=new User();
                populateEntityFromRow(entity,user,row);
                teacherRepository.save(entity);
                userrepository.save(user);
            }
            return teacherRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IOException("Error processing the Excel file");
        }
    }
    private void populateEntityFromRow(Teacher entity,User user,Row row) throws IOException
    {
        String password=String.valueOf(getStringCellValue(row.getCell(4)));
        entity.setTeacherId(getStringCellValue(row.getCell(0)));
        entity.setClassId(getStringCellValue(row.getCell(1)));
        entity.setEmail(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setName(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setPassword(password);

//        if (authentication != null && authentication.isAuthenticated())
//        {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                // Retrieve user roles
//                UserDetails userDetails = (UserDetails) principal;
//                role= String.valueOf(userDetails.getAuthorities());
//            }
//        }

        String role="ROLE_TEACHER";
        user.setUserId(String.valueOf(getStringCellValue(row.getCell(0))));
        user.setRole(role);
        user.setName(String.valueOf(row.getCell(3)));
        user.setPassword(password);
    }

    public List<Fees> processExcelFile2(MultipartFile file) throws IOException
    {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            List<Fees> stdList = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext())
            {
                rowIterator.next();
            }
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Fees entity = new Fees();
                populateEntityFromRow(entity,row);
                feerepository.save(entity);
            }
            return feerepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IOException("Error processing the Excel file");
        }
    }
    private void populateEntityFromRow(Fees entity,Row row) throws IOException
    {
        LocalDate date= LocalDate.now();
        entity.setBalance(String.valueOf(getStringCellValue(row.getCell(0))));
        entity.setDate(String.valueOf(date));
        entity.setId1(String.valueOf(getStringCellValue(row.getCell(1))));
        entity.setPaid(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setTransactionId(String.valueOf(getStringCellValue(row.getCell(3))));
        String id=String.valueOf(getStringCellValue(row.getCell(4)));
        Student s1=studentRepository.findById(id).get();
        entity.setFees(s1);
    }
}
