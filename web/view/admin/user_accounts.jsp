<%@ page import="controller.db_controller.*" %>
<%@ page import="model.*" %>
<%@ page import="java.util.List" %>

<jsp:include page="../header.jsp"/>
<%
    HttpSession sessionLogin = request.getSession(false);
    if (sessionLogin != null) {
        if (sessionLogin.getAttribute("uid") == null) {
//            response.sendRedirect("index.jsp");
%>
<jsp:forward page="../../index.jsp"/>
<%
        }
    }
%>
<style>
    @media (min-width: 992px) {
        #mainNav {
            padding-top: 0px;
            padding-bottom: 0px;
        }
    }

    .padding_5_txt {
        padding-left: 5px;
    }
</style>
<div style="margin-top: 80px;margin-left: 30px;margin-right: 30px">
    <div class="row">
        <div class="col-12" id="response">

        </div>
    </div>
    <div class="row" style="margin-top: 20px;text-align: center">
        <div class="col-1">
            Year
        </div>
        <div class="col-9">
            Degree
        </div>
        <div class="col-2">
            Batch
        </div>
    </div>
    <%--<form action="" method="post">--%>
    <div class="row">
        <div class="col-1">
            <select class="form-control" id="year">
                <%
                    {
                        List<BatchDTO> years = new BatchController().getYears();
                        for (BatchDTO batchDTO : years) {
                %>
                <option value="<%= batchDTO.getYear()%>"><%= batchDTO.getYear()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-9">
            <select class="form-control" id="degree">
                <%
                    {
                        List<DegreeDTO> allDegrees = new DegreeController().getAllDegrees();
                        for (DegreeDTO degreeDTO : allDegrees) {
                %>
                <option value="<%= degreeDTO.getDegid()%>"><%= degreeDTO.getDegreeName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-2">
            <select class="form-control" id="batch">
                <%
                    {
                        List<BatchDTO> allBatches = new BatchController().getAllBatches();
                        for (BatchDTO batchDTO : allBatches) {
                %>
                <option value="<%= batchDTO.getBatchid()%>"><%= batchDTO.getBatchName()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <%--<div class="col-2">--%>
        <%--<div>--%>
        <%--<button type="submit" id="btnSearch" class="btn" style="background-color: #ffbf05;width: 100%">Search</button>--%>
        <%--</div>--%>
        <%--</div>--%>
    </div>
    <%--</form>--%>
    <div class="row" style="margin-bottom: 50px;margin-top: 50px">
        <div class="col-12" style="padding: 0px">
            <table border="1px" style="width: 100%">
                <tr>
                    <th width="20%" style="text-align: center">Registration Number</th>
                    <th width="60%" style="text-align: center">Student Name</th>
                    <th width="15%" style="text-align: center">National ID</th>
                    <th width="5%" style="text-align: center">View</th>
                </tr>
                <tbody id="studentsDataBody">
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="margin-bottom: 50px">
        <div class="col-4"><i class="fa fa-arrow-circle-left fa-3x" style="float: right;margin-right: 30px" id="decPageNo"></i></div>
        <div class="col-4" style="text-align: center;font-size: 25px;font-weight: bold" id="pageNo"></div>
        <div class="col-4"><i class="fa fa-arrow-circle-right fa-3x" style="margin-left: 30px" id="incPageNo"></i></div>
    </div>

    <hr>

    <div class="row" style="margin-top: 50px">
        <div class="col-4">
            Registration Number :
        </div>
        <div class="col-8">
            <input class="form-control" type="text" id="regNo">
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-4">
            Student Name :
        </div>
        <div class="col-8">
            <input class="form-control" type="text" id="studetName">
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <div class="col-4">
            National ID :
        </div>
        <div class="col-8">
            <input class="form-control" type="text" id="nationalId">
        </div>
    </div>
    <div class="row" style="margin-top: 100px">
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnAdd" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Submit</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnUpdate" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Update</button>
            </div>
        </div>
        <div class="col-4">
            <div class="col-center" style="width: fit-content;margin: auto">
                <button id="btnDelete" class="btn" style="background-color: #ffbf05;margin-bottom: 50px">Delete</button>
            </div>
        </div>
    </div>
</div>
</div>
<%--</div>--%>
<jsp:include page="../footer.jsp"/>