@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRecordRepository cardRepo;

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return cardRepo.findByUserId(userId);
    }

    @Override
    public CreditCardRecord createCard(CreditCardRecord card) {
        return cardRepo.save(card);
    }

   
}
