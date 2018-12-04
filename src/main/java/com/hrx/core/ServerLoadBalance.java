package com.hrx.core;

import java.util.List;

public interface ServerLoadBalance {
	
	String  loadBalance(List<String> servers);

}
