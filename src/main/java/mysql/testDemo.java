package mysql;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * @author wyz
 * @create 2021-06-16 4:36
 */
public class testDemo {
    public static void main(String[] args) throws Exception {
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * from book WHERE bookId>1;";
        PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            System.out.print(rs.getString(1)+" ");
            System.out.println(rs.getString(2));
        }
    }
}
