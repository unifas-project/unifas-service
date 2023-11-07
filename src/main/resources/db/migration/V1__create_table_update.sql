


CREATE TABLE IF NOT EXISTS USERS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    EMAIL VARCHAR(255) NOT NULL,
    USERNAME VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    PHONE_NUMBER VARCHAR(20),
    ROLE VARCHAR(20) NOT NULL,
    IS_DELETED BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS ADDRESS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    STREET VARCHAR(255) NOT NULL,
    WARD VARCHAR(50) NOT NULL,
    DISTRICT VARCHAR(50) NOT NULL,
    CITY VARCHAR(50) NOT NULL,
    CONTACT VARCHAR(255) NOT NULL,
    RECEIVER VARCHAR(255) NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    USER_ID BIGINT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
    );

CREATE TABLE IF NOT EXISTS CATEGORY (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    GENDER VARCHAR(20),
    IS_SHOWN BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS SUB_CATEGORY (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    CATEGORY_ID BIGINT NOT NULL,
    IS_SHOWN BOOLEAN NOT NULL,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
    );

CREATE TABLE IF NOT EXISTS PRODUCT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    PRICE BIGINT NOT NULL,
    STOCK INT NOT NULL,
    DESCRIPTION TEXT,
    STAR INT,
    IS_DELETED BOOLEAN NOT NULL,
    CATEGORY_ID BIGINT NOT NULL,
    SUB_CATEGORY_ID BIGINT NOT NULL,
    FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID),
    FOREIGN KEY (SUB_CATEGORY_ID) REFERENCES SUB_CATEGORY(ID)
    );

CREATE TABLE IF NOT EXISTS COLOR (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS SIZE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    IS_DELETED BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS VARIANT (
     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     COLOR_ID BIGINT NOT NULL,
     SIZE_ID BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    FOREIGN KEY (COLOR_ID) REFERENCES COLOR(ID),
    FOREIGN KEY (SIZE_ID) REFERENCES SIZE(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
    );

CREATE TABLE IF NOT EXISTS IMAGE_PRODUCT (
     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    URL VARCHAR(255) NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
    );


CREATE TABLE IF NOT EXISTS REVIEW (
     ID BIGINT AUTO_INCREMENT PRIMARY KEY,
     CONTENT TEXT NOT NULL,
    TIME TIMESTAMP NOT NULL,
     STAR INT NOT NULL,
     USER_ID BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
    );

CREATE TABLE IF NOT EXISTS CART (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    USER_ID BIGINT NOT NULL,
    TOTAL_AMOUNT BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
    );

CREATE TABLE IF NOT EXISTS CART_PRODUCT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    QUANTITY INT NOT NULL,
    PRICE BIGINT NOT NULL,
    SUBTOTAL BIGINT NOT NULL,
    CART_ID BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    FOREIGN KEY (CART_ID) REFERENCES CART(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID)
    );

CREATE TABLE IF NOT EXISTS ORDERS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    DATE TIMESTAMP NOT NULL,
    TOTAL_AMOUNT BIGINT NOT NULL,
    ADDRESS VARCHAR(255),
    USER_ID BIGINT NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
    );

CREATE TABLE IF NOT EXISTS ORDER_LINE (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    QUANTITY INT NOT NULL,
    PRICE BIGINT  NOT NULL,
    SUBTOTAL BIGINT  NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,
    ORDER_ID BIGINT NOT NULL,
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT(ID),
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID)
    );

CREATE TABLE IF NOT EXISTS SALE_VOUCHER (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CODE VARCHAR(255) NOT NULL,
    DISCOUNT BIGINT NOT NULL,
    IS_DELETED BOOLEAN NOT NULL,
    ORDER_ID BIGINT NOT NULL,
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ID)
    );













































