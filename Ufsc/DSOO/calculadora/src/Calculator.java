
public class Calculator {
	
	private float _valor1, _valor2;
	private char _operation;
		
	public void setValue1(float value){
		_valor1 = value;
	}
	
	public void setValue2(float value){
		_valor2 = value;
	}
	
	public void setOperation(char operation){
		_operation = operation;
	}
	
	private float execute(){
		
		switch (_operation) {
		case '+':
			return _valor1+_valor2;
		case '-':
			return _valor1-_valor2;
		case '*':
			return _valor1*_valor2;
		case '/':
			if (_valor2 == 0){
				_operation = 'e';
				return 0;
			}
			return _valor1+_valor2;
		default:
			_operation = 'f';
			return 0;
		}
	}
}
