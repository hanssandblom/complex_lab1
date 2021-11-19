package se.iths.rest;


import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        Student createStudent = studentService.createStudent(student);
        if (createStudent == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("kunde inte hitta skapa student: ")
                            .type(MediaType.TEXT_PLAIN_TYPE).build()
            );
        }
        return Response.ok(student).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
            Student foundStudent = studentService.deleteStudent(id);
            if (foundStudent == null) {
                throw new WebApplicationException(
                        Response.status(Response.Status.NOT_FOUND)
                                .entity("kunde inte hitta user med id: " + id)
                                .type(MediaType.TEXT_PLAIN_TYPE).build()
                );
            }
            return Response.ok(foundStudent).build();
    }

    @Path("getallbylastname")
    @GET
    public Response getAllStudentsByLastName(@QueryParam("lastName") String lastName) {
        if (lastName == null) {
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("lastName parameter är oblikatoriskt")
                            .type(MediaType.TEXT_PLAIN_TYPE).build()
            );
        }
        List<Student> foundStudents = studentService.findStudentByLastName(lastName);
        return Response.ok(foundStudents).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Student foundStudent = studentService.findStudentById(id);
        return Response.ok(foundStudent).build();
    }

    @Path("updatefirstname/{id}")
    @PATCH
    public Response updateFirstName(@PathParam("id") Long id, @QueryParam("firstName") String firstName) {
        Student updateFirstName = studentService.updateFirstName(id, firstName);
        return Response.ok(updateFirstName).build();
    }

    @Path("updatelastname/{id}")
    @PATCH
    public Response updateLastName(@PathParam("id") Long id, @QueryParam("lastName") String lastName) {
        Student updateLastName = studentService.updateLastName(id, lastName);
        return Response.ok(updateLastName).build();
    }

    @Path("updateemail/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        Student updateEmail = studentService.updateEmail(id, email);
        return Response.ok(updateEmail).build();
    }

    @Path("phonenumber/{id}")
    @PATCH
    public Response updatePhoneNumber(@PathParam("id") Long id, @QueryParam("phoneNumber") String phoneNumber) {
        Student updatePhoneNumber = studentService.updatePhoneNumber(id, phoneNumber);
        return Response.ok(updatePhoneNumber).build();
    }
}
