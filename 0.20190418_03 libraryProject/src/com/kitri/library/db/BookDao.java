package com.kitri.library.db;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//조회 추가 삭제... 조작
public class BookDao extends DBConnector {
	PreparedStatement pstmt;
	ResultSet stmt;
	Vector<BookDto> list = new Vector<BookDto>();
	private static BookDao bookDao = new BookDao();
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
			e.printStackTrace();
		}
	}// end static classload

	public static BookDao getInstance() {
		return bookDao;
	}

	private BookDao() {
		setAllList();
	}

	// private BookDao(String url, String id, String pass) {
	// super(url, id, pass);
	// }// End BookDao

	public void insert(BookDto dto) {
		connect();
		String query = "insert into book values (?,?,?" + ",?,?,?" + ",?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.isbn);
			pstmt.setString(2, dto.name);
			pstmt.setString(3, dto.writer);
			pstmt.setString(4, dto.image_path);
			pstmt.setString(5, dto.genre);
			pstmt.setString(6, dto.publisher);
			pstmt.setString(7, dto.position);
			pstmt.setDate(8, dto.publish_date);
			pstmt.setString(9, dto.renting);
			pstmt.executeUpdate();
			System.out.println("BookDao 데이터베이스 입력 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("BookDao 데이터베이스 입력 실패");
			e.printStackTrace();
		}
		close();
	}// end insert

	// 모든 데이터 읽어오기
	public void setAllList() {
		if (connect()) {
			String query = "select * from book order by isbn";
			try {
				list = new Vector<BookDto>();
				pstmt = conn.prepareStatement(query);
				stmt = pstmt.executeQuery(query);
				while (stmt.next()) {
					BookDto bookDto = new BookDto(stmt.getString(1), stmt.getString(2), stmt.getString(3),
							stmt.getString(4), stmt.getString(5), stmt.getString(6), stmt.getString(7), stmt.getDate(8),
							stmt.getString(9));
					list.add(bookDto);
				} // end while

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();

	}// end selectListAll

	public int delete(String isbn) {
		String query = "delete from book where isbn = ?";
		if (connect()) {
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, isbn);
				int n = pstmt.executeUpdate();
				if (n > 0) {
					System.out.println("삭제 완료");
					return n;
				} else {
					System.out.println("삭제 실패");
					return n;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return 0;
	}// end delete

	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결 해제 실패");
			e.printStackTrace();
		}
	}

	public Vector<BookDto> getList() {
		return list;
	}

	public Vector<BookDto> getList(String searchMethodStr, String searchStr) {
		// String query ="";

		Vector<BookDto> retList = new Vector<BookDto>();

		if (searchMethodStr.equals("도서명")) {
			for (BookDto book : list) {
				if (book.name.contains(searchStr)) {
					retList.add(book);
				}
			}
			// query = "select from book where name like %"+searchMethodStr+"%";
		} else if (searchMethodStr.equals("저자")) {
			for (BookDto book : list) {
				if (book.writer.contains(searchStr)) {
					retList.add(book);
				}
			}
			// query = "select from book where writer like %"+searchMethodStr+"%";
		}

		return retList;
	}

	public Date getDateofStr(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		// Date parsed = format.parse("20110210");
		java.util.Date utilDate = null;
		try {
			utilDate = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}

	// public BookDto getBook(String isbn) {
	//
	// for (BookDto book : list) {
	// if (book.isbn.contains(isbn)) {
	// return book;
	// }
	// }
	// return null;
	// }

	public boolean upDateBook(String isbn, BookDto dto) {
		if (connect()) {
			String query = "update book set " + "isbn = ?," + "name = ?," + "writer= ?," + "image_path = ?,"
					+ "genre = ?," + "publisher = ?," + "position = ?," + "publish_date = ?,"
					+ "renting = ? where isbn like '" + isbn + "'";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, dto.isbn);
				pstmt.setString(2, dto.name);
				pstmt.setString(3, dto.writer);
				pstmt.setString(4, dto.image_path);
				pstmt.setString(5, dto.genre);
				pstmt.setString(6, dto.publisher);
				pstmt.setString(7, dto.position);
				pstmt.setDate(8, dto.publish_date);
				pstmt.setString(9, dto.renting);
				pstmt.executeUpdate();
				System.out.println("BookDao upDateBook 데이터베이스 변경 성공");
				close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("BookDao upDateBook 데이터베이스 입력 실패");
				e.printStackTrace();
				close();
				return false;
			}
		}
		close();
		return false;
	}// end updateBookwithIsbn

	public BookDto select(String isbn) {
		if (connect()) {
			String query = "select * from book where isbn like ?";
			try {
				list = new Vector<BookDto>();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, isbn);
				stmt = pstmt.executeQuery();
				while (stmt.next()) {
					BookDto bookDto = new BookDto(stmt.getString(1), stmt.getString(2), stmt.getString(3),
							stmt.getString(4), stmt.getString(5), stmt.getString(6), stmt.getString(7), stmt.getDate(8),
							stmt.getString(9));
					return bookDto;
				} // end while

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return null;
	}// end select

	public Vector<BookDto> selectOfMem(String memberId) {
		if (connect()) {
			String query = "select * from book where renting like ?";
			try {
				Vector<BookDto> veclist = new Vector<BookDto>();
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, memberId);
				stmt = pstmt.executeQuery();
				while (stmt.next()) {
					BookDto bookDto = new BookDto(stmt.getString(1), stmt.getString(2), stmt.getString(3),
							stmt.getString(4), stmt.getString(5), stmt.getString(6), stmt.getString(7), stmt.getDate(8),
							stmt.getString(9));
					veclist.add(bookDto);
				} // end while
				return veclist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close();
		return null;
	}

}// end BookDao class
