package Entity;

import Entity.Abstract.AEmployee;
import Entity.Interface.ICreditAccount;
import Entity.Interface.ILoanOfficer;

public class LoanOfficer extends AEmployee implements ILoanOfficer
{

    @Override
    public void CreateAccount(ICreditAccount account, Customer customer)
    {

    }

    @Override
    public void CloseAccount(ICreditAccount account)
    {

    }

}
