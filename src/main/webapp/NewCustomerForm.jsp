	<label for="name"><span>Full Name</span>
		<input name="name" required="true"/>
	</label>
	<label for="email"><span>Email</span>
		<input name="email" type="email" required="true"/>
	</label>
	<label for="address"><span>Address</span>
		<textarea name="address" required="true"></textarea>
	</label>
	<label for="phone"><span>Phone</span>
	<input name="phone" pattern="[0-9]{10}" minlength="10" maxlength="10" required="true"/>
	</label>
	<label for="adhaar"><span>Adhaar Number</span>
		<input name="adhaar" maxlength="12" required="true"/>
	</label>
	<label for="pan"><span>Pan Number</span>
		<input name="pan" required="true"/>
	</label>
	<label for="dob"><span>DOB</span>
		<input name="dob" type="date" required="true"/>
	</label>
	<button type="submit">Submit</button>
