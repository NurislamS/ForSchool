package kfu.project.service.impl;

import kfu.project.entity.*;
import kfu.project.repository.*;
import kfu.project.service.converter.TaskFormToTaskConverter;
import kfu.project.service.exception.AccessDeniedException;
import kfu.project.service.exception.NotFound.UserNotFoundException;
import kfu.project.service.form.QuestionForm;
import kfu.project.service.form.StudentForm;
import kfu.project.service.form.StudentsForTaskFrom;
import kfu.project.service.form.TaskForm;
import kfu.project.service.intrface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nurislam on 18.07.2018.
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskFormToTaskConverter taskFormToTaskConverter;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Task save(TaskForm taskForm) throws UserNotFoundException {
        if(taskForm == null){
            throw new UserNotFoundException();
        }
        Task task = taskFormToTaskConverter.convert(taskForm);
        taskRepository.save(task);
        testRepository.save(task.getTests());
        questionRepository.save(task.getQuestions());
//        for(Test test: task.getTests()){
//
//        }
        return task;
    }

    @Override
    public Set<Task> getAllByTeacher(String token) {
        User user = userRepository.findOneByToken(token);
        Teacher teacher = teacherRepository.getByUser(user);
        return taskRepository.getAllByTeacher(teacher);
    }

    @Override
    public void deleteByIdAndToken(Long id, String token) throws AccessDeniedException {
        Teacher teacher = teacherRepository.getByUser(userRepository.findOneByToken(token));
        if(teacher != null){
            taskRepository.delete(id);
        } else throw new AccessDeniedException();
    }

    @Override
    public void addStudent(StudentsForTaskFrom taskFrom) throws AccessDeniedException, UserNotFoundException {
        Task task = taskRepository.findById(taskFrom.getId());
        if(task == null){
            throw new UserNotFoundException();
        }
        Set<Student> students = task.getStudents();
        if(students == null){
            students = new HashSet<>();
        }
        for(StudentForm form: taskFrom.getStudentForms()){
            students.add(studentRepository.findOne(form.getId()));
        }
        task.setStudents(students);
        taskRepository.save(task);
    }

}
