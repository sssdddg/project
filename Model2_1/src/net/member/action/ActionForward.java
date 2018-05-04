package net.member.action;

public class ActionForward {
		//	�̵���� ���� 	// ��� true - respense , false - forward
		boolean isRedirest;
		//	�̵���� ����
		String path;
		
		public boolean isRedirest() {
			return isRedirest;
		}
		public void setRedirest(boolean isRedirest) {
			this.isRedirest = isRedirest;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
}
