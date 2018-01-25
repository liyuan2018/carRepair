package com.cys.constants;

/**
 * Created by liyuan on 2018/1/24.
 */
public interface ErrorCode {
    public static enum Common implements IMessage {
        invalidErrorCode(100),
        beanInstanceError(101),
        resourceNotFound(102),
        objectTransferError(103),
        fileNotFound(200),
        fileSizeNotAllow(201),
        uploadFailed(202),
        downloadFailed(203),
        uploadWriteFailed(204),
        dbConnectError(300),
        paramNotNull(1000),
        invalidNotNull(1001),
        invalidParamLength(1002),
        paramNotMatch(1003),
        invalidDataFormat(1004),
        regexNotMatch(1005),
        invalidFileFormat(1006),
        invalidMethodArgument(1007),
        beanSerializeError(1008),
        paramIsTrue(1009),
        invalidIsTrue(1010),
        paramIsNull(1011),
        invalidIsNull(1012),
        paramHasLength(1013),
        invalidHasLength(1014),
        paramHasText(1015),
        invalidHasText(1016),
        invalidNotContainArgs(1017),
        invalidNotContain(1018),
        invalidArrayNotEmptyArgs(1019),
        invalidArrayNotEmpty(1020),
        invalidElementsNotNullArgs(1021),
        invalidElementsNotNull(1022),
        invalidCollectionNotEmptyArgs(1023),
        invalidCollectionNotEmpty(1024),
        invalidMapNotEmptyArgs(1025),
        invalidMapNotEmpty(1026),
        invalidIsInstanceOfArgs(1027),
        invalidIsInstanceOf(1028),
        invalidIsAssignableArgs(1029),
        invalidIsAssignable(1030),
        pwdNotMatch(1031),
        fieldConfirmError(1032),
        userNotRegister(1101),
        userLocked(1102),
        sessionTimeout(1103),
        notPermissions(1104),
        userPwdNotMatch(1100),
        userPwdRepeat(1033);

        private int code;
        private String category;

        private Common(int code) {
            this.code = code;
            this.category = this.getClass().getSimpleName();
        }

        public int getCode() {
            return this.code;
        }

        public String getCategory() {
            return this.category;
        }
    }
}