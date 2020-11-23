- Link: https://www.baeldung.com/hibernate-notnull-vs-nullable
# Ý nghĩa
- Về ý nghĩa. @NotNull và @Column(nullable = false)  đều được sử dụng ddeert validate dữ liệu not null khi chúng ta call save data đến database
# Sự khác biệt
##  @Column(nullable = false)
- Sử dụng  @Column(nullable = false), khi chúng sử dụng auto tạo ddl của hibernate. Hibernate thêm điều kiện not null cho vào câu lệnh tạo table cho chúng ta.
 ![alt text](/docs/image/column/Image1.png)
 ![alt text](/docs/image/column/image2.png)
 ![alt text](/docs/image/column/Image3.png)
- Khi validate dữ liệu. Với @Column(nullable = false) hibernate sẽ không validate tại app, thay vào đó hibernate sẽ gửi lệnh save lên server database sau đó server hibernate bắn lỗi.

## @NotNull
- Sử dụng @NotNull, Khi chúng ta auto tạo DDL của Hibernate. Mặc định Hibernate sẽ không thêm điều kiện not null vào câu lệnh tạo table.
 ![alt text](/docs/image/column/Image1.png)
 ![alt text](/docs/image/nonnul/Image4.png)
 ![alt text](/docs/image/nonnul/Image5.png)
 ![alt text](/docs/image/nonnul/Image6.png)
- Khi validate dữ liệu. Với @NotNull, thì hibernate sẽ validate ngay tại local của app. Hibernate sẽ không save lên database để kiểm tra not null.
- khi sử dụng @NotNull Hibernate sẽ không auto tạo ddl check not null. Tuy nhiên nếu chúng ta vẫn muốn tạo thì có thể thêm thuộc tính này để hibernate thêm thuộc tính validate vào câu lệnh reg ddl
 ![alt text](/docs/image/nonnul/Image7.png)

 
